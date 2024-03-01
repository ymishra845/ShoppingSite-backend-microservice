package com.ShoppingBackend.User.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ShoppingBackend.User.DTO.RequestBody.AddOrderRequestBody;
import com.ShoppingBackend.User.DTO.RequestBody.AddOrderVsProductRequestBody;
import com.ShoppingBackend.User.DTO.RequestBody.OrderDto;
import com.ShoppingBackend.User.DTO.RequestBody.OrderProductDto;
import com.ShoppingBackend.User.DTO.ResponseBody.Bill;
import com.ShoppingBackend.User.DTO.ResponseBody.BillProductDetailsDto;
import com.ShoppingBackend.User.DTO.ResponseBody.OrderDetalilsResponseBody;
import com.ShoppingBackend.User.DTO.ResponseBody.ProductResponseBody;
import com.ShoppingBackend.User.Exception.UnavailableProduct;
import com.ShoppingBackend.User.Utils.ApiUrlUtil;
import com.ShoppingBackend.User.Utils.RequesterUtil;
import com.ShoppingBackend.User.Utils.ShoppingLogger;

@Service
public class BuyerService {

	@Autowired
	RequesterUtil requesterUtil;
	
	public Bill orderProduct(OrderDto orderDto) {
		ShoppingLogger.logger.info("Inside service layer will call db API to have product detail");
		// will call DB API to get details of every product we will check are we able to place order or not 
		// if any product is having the less quantity our API will provide response accordingly
		
		List<OrderProductDto> orderProducts= orderDto.getOrderProductDto();
		List<ProductResponseBody>products= new ArrayList<ProductResponseBody>();// in this array we are having databse quantity
		
		
		int totalPrice =0;
		int totalQuantity=0;
		
		for(OrderProductDto orderProductDto :orderProducts) {
			UUID productId= orderProductDto.getProductId();
			
			// will call  DB API to get specific product detail 
			String url= ApiUrlUtil.dbApiURL + "/product/get" + "?productId=" + productId.toString();
			RestTemplate requestor= requesterUtil.getRestTemplate();
			HttpHeaders header= requesterUtil.getHeader();
			HttpEntity httpEntity= requesterUtil.createHttpEntity(header);
	           ResponseEntity<ProductResponseBody> product= requestor.exchange(url, HttpMethod.GET,httpEntity, ProductResponseBody.class);
	           ProductResponseBody pBody= product.getBody();
	           if(pBody.getQuantity() < orderProductDto.getQuantity()) {
	        	   throw new UnavailableProduct(String.format("Product %s is having less quantity %d",pBody.getProductName(),pBody.getQuantity()));
	           }
	           totalQuantity+= orderProductDto.getQuantity();
	           totalPrice +=pBody.getPrice()*orderProductDto.getQuantity();
	           products.add(product.getBody());
		}
		ShoppingLogger.logger.info("Got all the product present in the request");
		//create one order 
		
		String orderURL = ApiUrlUtil.dbApiURL + "/order/add";
		
		HttpHeaders orderHeader= requesterUtil.getHeader();
		AddOrderRequestBody addOrderRequestBody= new AddOrderRequestBody(orderDto.getUserId(),totalQuantity,totalPrice);
		HttpEntity entity= requesterUtil.creatHttpEntity(addOrderRequestBody,orderHeader);
		RestTemplate orderRequestor= requesterUtil.getRestTemplate();
		
		ResponseEntity<OrderDetalilsResponseBody> orderResp= orderRequestor.exchange(orderURL, HttpMethod.POST, entity, OrderDetalilsResponseBody.class);
		
		OrderDetalilsResponseBody order= orderResp.getBody();
		
		
		//and update OrderProduct Api
		
		List<BillProductDetailsDto> billProducts= new ArrayList<>();
		
		for(int i=0;i<products.size();i++) {
			int userQuantity= orderProducts.get(i).getQuantity();
			int dbQuantity= orderProducts.get(i).getQuantity();
			int price = userQuantity * products.get(i).getPrice();
			UUID productId= products.get(i).getId();
			UUID orderId= order.getId();
			
			AddOrderVsProductRequestBody addOrderVsProductRequestBody= new AddOrderVsProductRequestBody(productId,orderId,price,userQuantity);
			
			//call db API to register product in that particular order
			String opUrl= ApiUrlUtil.dbApiURL+ "/order/register";
			HttpEntity opEntity= requesterUtil.creatHttpEntity(addOrderVsProductRequestBody, orderHeader);
			
			RestTemplate opRequester= requesterUtil.getRestTemplate();
			ResponseEntity<AddOrderVsProductRequestBody> opResp1= opRequester.exchange(opUrl, HttpMethod.POST,opEntity,AddOrderVsProductRequestBody.class);
			
			BillProductDetailsDto billProductDetailsDto=new BillProductDetailsDto(productId,userQuantity,products.get(i).getProductName(),price);
			billProducts.add(billProductDetailsDto);
			//update the product quantity into database
			int updateQuantity= dbQuantity-userQuantity;
			String updatePUrl= ApiUrlUtil.dbApiURL +"/product/update?" + "productId=" +productId.toString() +"&quantity=" +updateQuantity;
			ResponseEntity resp= opRequester.exchange(updatePUrl, HttpMethod.POST, new HttpEntity<>(orderHeader),Object.class);
		}
		
		//create bill
		
		Bill bill = new Bill(order.getId(),billProducts,totalQuantity,totalPrice);
		
		return bill;
		
		
		
		
		
	}
}
