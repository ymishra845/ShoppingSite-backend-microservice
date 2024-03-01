package com.ShoppingBackend.User.DTO.ResponseBody;

import java.util.UUID;

import com.ShoppingBackend.User.DTO.GeneralDTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseBody {

	UUID Id;
	String productName;
	int price;
	int quantity;
	UserDTO seller;
	double rating;
	String productType;
}
