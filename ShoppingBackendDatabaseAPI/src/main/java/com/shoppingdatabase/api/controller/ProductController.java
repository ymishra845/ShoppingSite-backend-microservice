package com.shoppingdatabase.api.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingdatabase.api.DTO.ProductReqBody;
import com.shoppingdatabase.api.models.PortalUser;
import com.shoppingdatabase.api.models.Product;
import com.shoppingdatabase.api.repository.PortalUserRepo;
import com.shoppingdatabase.api.repository.ProductRepo;

@RestController
@RequestMapping("/db/product")
public class ProductController {

	@Autowired
	PortalUserRepo portalUserRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@PostMapping("/add")
	public ProductReqBody addProduct(@RequestBody ProductReqBody product) {
		
		UUID id= UUID.randomUUID();
		
		Product p= new Product();
		p.setId(id);
		p.setProductName(product.getProductName());
		p.setProductType(product.getProductType());
		p.setRating(0);
		p.setQuantity(product.getQuantity());
		p.setPrice(product.getPrice());
		
		PortalUser seller = portalUserRepo.findById(product.getSellerId()).orElse(null);
		p.setSeller(seller);
		
		productRepo.save(p);
		product.setSellerId(id);
		
		return product;
	}
	@GetMapping("/get")
	public Product getProduct(@RequestParam UUID productId) {
		return productRepo.findById(productId).orElse(null);
	}
	
	@PutMapping("/update")
	public ResponseEntity updateQuantity(@RequestParam UUID productId, @RequestParam int quantity) {
		Product product= productRepo.findById(productId).orElse(null);
		product.setQuantity(quantity);
		productRepo.save(product);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	
}
