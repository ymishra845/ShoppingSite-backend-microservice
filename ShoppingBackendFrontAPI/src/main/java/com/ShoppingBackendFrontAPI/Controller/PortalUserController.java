package com.ShoppingBackendFrontAPI.Controller;

import java.net.http.HttpHeaders;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ShoppingBackendFrontAPI.DTO.UserSignUpDTO;

@RestController
@RequestMapping("/user")
public class PortalUserController {

	@PostMapping("/signUp")
	public ResponseEntity<UserSignUpDTO> signUp(@RequestBody UserSignUpDTO userSignUpDTO) {
		
		String url= "http://localhost:8081/db/user/add";
		org.springframework.http.HttpHeaders headers= new org.springframework.http.HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<UserSignUpDTO> entity= new HttpEntity<UserSignUpDTO>(userSignUpDTO,headers);
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.exchange(url, HttpMethod.POST, entity, UserSignUpDTO.class);
		
	}
	
}
