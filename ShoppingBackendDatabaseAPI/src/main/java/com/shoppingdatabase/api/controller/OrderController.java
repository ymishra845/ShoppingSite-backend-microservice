package com.shoppingdatabase.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingdatabase.api.DTO.RequestBody.AddOrderDto;
import com.shoppingdatabase.api.DTO.RequestBody.OrderProductDetailsRequestBody;
import com.shoppingdatabase.api.models.OrderDetails;
import com.shoppingdatabase.api.models.OrderVsProduct;
import com.shoppingdatabase.api.models.PortalUser;
import com.shoppingdatabase.api.repository.OrderDetailsRepository;
import com.shoppingdatabase.api.repository.OrderVsProductRepo;
import com.shoppingdatabase.api.repository.PortalUserRepo;

@RestController
@RequestMapping("/db/order")
public class OrderController {

	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	PortalUserRepo portalUserRepo;
	
	@Autowired
	OrderVsProductRepo orderVsProductRepo;
	
     @PostMapping("/add")
     public OrderDetails addOrder(@RequestBody AddOrderDto addOrderDto) {
    	 OrderDetails orderDetails= new OrderDetails();
    	 PortalUser portalUser = portalUserRepo.findById(addOrderDto.getUserId()).orElse(null);
    	 orderDetails.setUser(portalUser);
    	 orderDetails.setQuantity(addOrderDto.getQuantity());
    	 orderDetails.setTotalPrice(addOrderDto.getTotalPrice());
    	 orderDetailsRepository.save(orderDetails);
    	 return orderDetails;
     }
     
     @PostMapping("/register")
     public ResponseEntity registerProduct(@RequestBody OrderProductDetailsRequestBody orderProductDetailsRequestBody) {
    	 
    	 OrderVsProduct orderVsProduct= new OrderVsProduct();
    	 orderVsProduct.setOrderId(orderProductDetailsRequestBody.getOrderId());
    	 orderVsProduct.setProductId(orderProductDetailsRequestBody.getProductId());
    	 orderVsProduct.setTotalPrice(orderProductDetailsRequestBody.getPrice());
    	 orderVsProduct.setTotalQuantity(orderProductDetailsRequestBody.getQuantity());
    	 orderVsProductRepo.save(orderVsProduct);
    	 
    	 return new ResponseEntity<>(orderProductDetailsRequestBody,HttpStatus.CREATED);
    	 
    	 
    	 
     }
}
