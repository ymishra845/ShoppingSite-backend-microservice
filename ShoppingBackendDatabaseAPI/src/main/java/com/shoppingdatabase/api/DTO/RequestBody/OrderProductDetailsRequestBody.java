package com.shoppingdatabase.api.DTO.RequestBody;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderProductDetailsRequestBody {

	UUID productId;
	UUID orderId;
	int price;
	int quantity;
}
