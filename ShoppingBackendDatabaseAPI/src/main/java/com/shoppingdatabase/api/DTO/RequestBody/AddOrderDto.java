package com.shoppingdatabase.api.DTO.RequestBody;

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
public class AddOrderDto {

	UUID userId;
	int quantity;
	int totalPrice;
	
	
}
