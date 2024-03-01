package com.shoppingdatabase.api.DTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductReqBody {

	String productName;
	int price;
	int quantity;
	String productType;
	UUID sellerId;
}
