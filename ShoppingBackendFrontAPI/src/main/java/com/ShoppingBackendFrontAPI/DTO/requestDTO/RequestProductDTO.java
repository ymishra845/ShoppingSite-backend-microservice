package com.ShoppingBackendFrontAPI.DTO.requestDTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDTO {

	String productName;
	int price;
	int quantity;
	UUID sellerId;
	String productType;
}
