package com.ShoppingBackendFrontAPI.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ShoppingBackendFrontAPI.DTO.Response.BillProductDetailsDto;
import com.ShoppingBackendFrontAPI.DTO.requestDTO.OrderDto;

@RestController
@RequestMapping("/user")
public class BuyerUserController {

	@PostMapping("/order")
	public ResponseEntity placeOrder(@RequestBody OrderDto orderDto) {
		
		String url="http://localhost:8084/user/buyer/order";
		HttpHeaders headers= new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity httpEntity= new HttpEntity(orderDto,headers);
		RestTemplate restTemplate= new RestTemplate();
		ResponseEntity billResp= restTemplate.exchange(url, HttpMethod.POST, httpEntity,BillProductDetailsDto.class);
		
		return billResp;
		
	}
}
