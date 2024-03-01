package com.ShoppingBackendFrontAPI.DTO.Response;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillResponseDto {

	UUID orderId;
	List<BillProductDetailsDto> products;
	int totalQuantity;
	int totalPrice;
	
}
