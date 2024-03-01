package com.ShoppingBackend.Email.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingBackend.Email.DTO.requestDTO.AddProductDTO;
import com.ShoppingBackend.Email.DTO.responseDTO.GeneralMessage;
import com.ShoppingBackend.Email.Service.SellerMailService;

@RestController
@RequestMapping("/mail/seller")
public class SellerController {

	@Autowired
	SellerMailService sellerMailService;
	
	@PostMapping("/addproduct")
	public ResponseEntity ResponseEntity(@RequestBody AddProductDTO addProductDTO) {
		
		try {
			sellerMailService.sendAddProductMail(addProductDTO);
			return new ResponseEntity(new GeneralMessage(true),HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(new GeneralMessage(false),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
