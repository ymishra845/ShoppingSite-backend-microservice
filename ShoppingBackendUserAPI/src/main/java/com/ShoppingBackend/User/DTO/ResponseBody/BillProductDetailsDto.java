package com.ShoppingBackend.User.DTO.ResponseBody;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillProductDetailsDto {

	UUID productId;
	int quantity;
	String productName;
	int price;
}
