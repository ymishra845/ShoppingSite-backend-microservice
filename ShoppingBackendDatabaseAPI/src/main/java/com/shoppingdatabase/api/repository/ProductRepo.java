package com.shoppingdatabase.api.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.shoppingdatabase.api.models.Product;

@Repository
public interface ProductRepo extends MongoRepository<Product, UUID>{
	
}
