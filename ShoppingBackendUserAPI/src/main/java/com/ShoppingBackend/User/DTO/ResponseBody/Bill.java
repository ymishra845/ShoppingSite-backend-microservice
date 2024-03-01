package com.ShoppingBackend.User.DTO.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

	UUID orderId;
	List<BillProductDetailsDto> products;
	int totalQuantity;
	int totalPrice;
}
