package com.shoppingdatabase.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingdatabase.api.DTO.RequestBody.AddAccessDTO;
import com.shoppingdatabase.api.DTO.ResponseBody.ACLConfigDTO;
import com.shoppingdatabase.api.models.ACL;
import com.shoppingdatabase.api.repository.ACLRepository;

@RestController
@RequestMapping("db/acl")
public class ACLController {

	@Autowired
	ACLRepository aclRepository;
	
	@PostMapping("/add")
	public void registerAccess(@RequestBody AddAccessDTO addAccessDTO) {
		ACL acl= new ACL();
		acl.setOperation(addAccessDTO.getOperation());
		acl.setRequestor(addAccessDTO.getRequestor());
		aclRepository.save(acl);
	}
	
	@GetMapping("/validate")
	public ResponseEntity isAccessAvailable(@RequestParam String requestor, @RequestParam String operation) {
		ACL acl=aclRepository.getConfiguration(requestor, operation);
		
		if(acl!= null) {
			return new ResponseEntity<>(new ACLConfigDTO(true),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(new ACLConfigDTO(false),HttpStatus.OK);
		}
		
		
		
	}
}
