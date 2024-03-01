package com.ShoppingBackend.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.ShoppingBackend.User.DTO.GeneralDTO.GeneralMessageDTO;
import com.ShoppingBackend.User.DTO.RequestBody.SellerProductRegistrationDTO;
import com.ShoppingBackend.User.Exception.MailNotSendException;
import com.ShoppingBackend.User.Exception.UserDoesNotExistException;
import com.ShoppingBackend.User.Service.SellerService;
import com.ShoppingBackend.User.Utils.ShoppingLogger;

@RestController
@RequestMapping("/user/seller")
public class SellerController {

	@Autowired
	SellerService sellerService;
	
	
	@PostMapping("/product/register")
	public ResponseEntity registerProduct(@RequestBody SellerProductRegistrationDTO sellerProductRegistrationDTO) {
		
		//DataBase Api -> Product will register in database;
		//Email API -> Notify seller that your product is registered
		ShoppingLogger.logger.info("Request receive in sellerService");
		
	   try {
		
		   SellerProductRegistrationDTO resp= sellerService.register(sellerProductRegistrationDTO);
		   return new ResponseEntity(resp,HttpStatus.CREATED);
	} catch (Unauthorized unauthorized) {
		return new ResponseEntity(new GeneralMessageDTO(unauthorized.getMessage()),HttpStatus.UNAUTHORIZED);
	}catch(UserDoesNotExistException userDoesNotExistException){
		return new ResponseEntity(new GeneralMessageDTO(userDoesNotExistException.getMessage()),HttpStatus.NOT_FOUND);
	}catch(MailNotSendException mailNotSendException){
		return new ResponseEntity(new GeneralMessageDTO(mailNotSendException.getMessage()),HttpStatus.BAD_GATEWAY);
	}
	   catch(RuntimeException runtimeException) {
		return new ResponseEntity(new GeneralMessageDTO(runtimeException.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}  
	   
		
	}
	
}
