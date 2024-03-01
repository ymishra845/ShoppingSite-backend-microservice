package com.ShoppingBackendFrontAPI.DTO.Response;

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
	String productName;
	int quantity;
	int price;
}
