package com.shoppingdatabase.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingdatabase.api.models.PortalUser;
import com.shoppingdatabase.api.repository.PortalUserRepo;

@RestController
@RequestMapping("/db/user")
public class PortalUserController {

	@Autowired
	PortalUserRepo portalUserRepo;
	
	@PostMapping("/add")
	public PortalUser addUser(@RequestBody PortalUser portalUser) {
		portalUserRepo.save(portalUser);
		return portalUser;
		
	}
	
	@GetMapping("/getuser")
	public PortalUser getUserById(@RequestParam UUID id) {
		return portalUserRepo.findById(id).orElse(null);
	}
}
