package com.ShoppingBackendFrontAPI.Controller;

import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ShoppingBackendFrontAPI.DTO.UserSignUpDTO;
import com.ShoppingBackendFrontAPI.DTO.requestDTO.ACLConfigDTO;
import com.ShoppingBackendFrontAPI.DTO.requestDTO.GeneralMessageDTO;
import com.ShoppingBackendFrontAPI.DTO.requestDTO.RequestProductDTO;
import com.ShoppingBackendFrontAPI.DTO.requestDTO.SellerProductRegistrationDTO;

@RestController
@RequestMapping("/user/seller")
public class SellerUserController {

	@PostMapping("/register")
	public ResponseEntity registerProduct(@RequestBody RequestProductDTO requestProductDTO) {
		
		// SellerId -> with the help of sellerId i can get tge user object
		//call DB API to get user object
	    UUID sellerId= requestProductDTO.getSellerId();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity httpEntity = new HttpEntity(headers);
		String urlDb="http://localhost:8081/db/user/getuser?id=" + sellerId;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<UserSignUpDTO> respUser= restTemplate.exchange(urlDb, HttpMethod.GET,httpEntity,UserSignUpDTO.class);
		
		//database API for ACL validation check
		
		String urlAcl= "http://localhost:8081/db/acl/validate?"+"requestor="+respUser.getBody().getType()+"&"+"operation="+"ADDPRODUCT";
		ResponseEntity<ACLConfigDTO> aclResp= restTemplate.exchange(urlAcl, HttpMethod.GET, httpEntity, ACLConfigDTO.class);
		
		if(aclResp.getBody().isValidConfig()==false) {
			
			return new ResponseEntity(new GeneralMessageDTO(String.format("User with id %id doesn't have access", sellerId.toString())),HttpStatus.UNAUTHORIZED);
			
		}
		
		String sellerUserURL="http://localhost:8084/user/seller/product/register";
		HttpEntity httpEntity2= new HttpEntity(requestProductDTO,headers);
		
		ResponseEntity<SellerProductRegistrationDTO> sellerResp= restTemplate.exchange(sellerUserURL, HttpMethod.POST, httpEntity,SellerProductRegistrationDTO.class);
		
		return sellerResp;
		
		
		
	}
}
