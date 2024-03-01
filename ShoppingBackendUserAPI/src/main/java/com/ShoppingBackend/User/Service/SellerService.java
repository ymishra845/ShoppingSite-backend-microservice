package com.ShoppingBackend.User.Service;

import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ShoppingBackend.User.DTO.GeneralDTO.UserDTO;
import com.ShoppingBackend.User.DTO.RequestBody.AddProductMailReqBody;
import com.ShoppingBackend.User.DTO.RequestBody.SellerProductRegistrationDTO;
import com.ShoppingBackend.User.DTO.ResponseBody.MailResponseDto;
import com.ShoppingBackend.User.Exception.MailNotSendException;
import com.ShoppingBackend.User.Exception.UnAuthorized;
import com.ShoppingBackend.User.Exception.UserDoesNotExistException;
import com.ShoppingBackend.User.Utils.ApiUrlUtil;
import com.ShoppingBackend.User.Utils.ShoppingLogger;

@Service
public class SellerService {

	public SellerProductRegistrationDTO register(SellerProductRegistrationDTO sellerProductRegistrationDTO) {
		UUID sellerId= sellerProductRegistrationDTO.getSellerId();
		
		//Need to check that this user is a registered user or not
		//if it is not raise the exception
		//if it is registered then check this user is a seller or not
		//if it is not seller throw the unauthorized exception
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<UserDTO> httpEntity= new HttpEntity<UserDTO>(httpHeaders);
		String url="http://localhost:8081/db/user/getuser?id=" + sellerId.toString();
		
		
		ResponseEntity<UserDTO> userResp= restTemplate.exchange(url, HttpMethod.GET, httpEntity, UserDTO.class);
		
		if(userResp.getBody()==null) {
			throw new UserDoesNotExistException(String.format("User with id %s does not exist in system",sellerId.toString()));
		}
		if(userResp.getBody().getType().equals("SELLER")) {
			throw new UnAuthorized(String.format("User with id %s does not have access of this operation", sellerId.toString()));
		}
		
		
		// Database API call to save the product
		
	
		
		String urlProduct="http://localhost:8081/db/product/add";
		
		HttpEntity httpEntityP= new HttpEntity(sellerProductRegistrationDTO,httpHeaders);
		
		ResponseEntity<SellerProductRegistrationDTO> respProduct= restTemplate.exchange(urlProduct, HttpMethod.POST, httpEntityP, SellerProductRegistrationDTO.class);
		if(respProduct.getBody()==null) {
			throw new RuntimeException(String.format("Product addition was not successfull!!"));
		}
		
		
		// Mail Service
		
		
		
		String mailUrl= ApiUrlUtil.mailApiUrl+ "/seller/addproduct";
		
		String mailText=generateSellerMailForAddProduct(userResp.getBody().getName(), sellerProductRegistrationDTO.getProductName(),sellerProductRegistrationDTO.getPrice() , sellerProductRegistrationDTO.getQuantity(),sellerProductRegistrationDTO.getProductType());
		
		AddProductMailReqBody addProductMailReqBody= new AddProductMailReqBody(userResp.getBody().getEmail(), mailText ,"Congratulations!! Product got added",userResp.getBody().getName());
		
		HttpEntity mailEntity= new HttpEntity(addProductMailReqBody, httpHeaders);
		
		ResponseEntity<MailResponseDto> mailResp= restTemplate.exchange(mailUrl, HttpMethod.POST, mailEntity , MailResponseDto.class);
		
		
		if(!mailResp.getBody().isSuccess()) {
			
			throw new MailNotSendException(String.format("Mail not send to the user with id %s", userResp.getBody().getId()));
		}
		
		
		return respProduct.getBody();
		
		
	}
	
	public String generateSellerMailForAddProduct(String sellerName,String productName, int price, int quantity, String productType) {
		String mailText= String.format("Hii %s\n" +"Your product got added into our shopping website and Below is your product details: \n" +
	   "1. ProductName : %s\n" +
	   "2. Price : %d\n" +
	   "3. Quantity : %d\n" +
	   "4. ProductType : %s\n",sellerName,productName,price,quantity,productType);
		return mailText;
	}
	
	
	
	
	
}
