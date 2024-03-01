package com.ShoppingBackend.User.DTO.RequestBody;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddOrderVsProductRequestBody {

	UUID productId;
	UUID orderId;
	int price;
	int quantity;
}
