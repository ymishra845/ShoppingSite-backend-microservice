package com.ShoppingBackend.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingBackend.User.DTO.RequestBody.OrderDto;
import com.ShoppingBackend.User.DTO.ResponseBody.Bill;
import com.ShoppingBackend.User.Service.BuyerService;
import com.ShoppingBackend.User.Utils.ShoppingLogger;

@RestController
@RequestMapping("/user/buyer")
public class BuyerController {

	@Autowired
	BuyerService buyerService;
	
	
	@PostMapping("/order")
	public ResponseEntity placeOrder(@RequestBody OrderDto orderDto) {
		ShoppingLogger.logger.info("Request received : Buyer Controller" +orderDto.toString());
		//call service
		
		Bill bill=buyerService.orderProduct(orderDto);
		
		return new ResponseEntity(bill,HttpStatus.CREATED);
		
		
	}
}
