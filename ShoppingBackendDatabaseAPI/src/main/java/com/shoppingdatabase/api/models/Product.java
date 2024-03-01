package com.shoppingdatabase.api.models;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


//User -> One user can have many product -> One to many



@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product {

	@Id
	UUID id;
	String productName;
	int price;
	int quantity;
	PortalUser seller;
	double rating;
	String productType;
}
