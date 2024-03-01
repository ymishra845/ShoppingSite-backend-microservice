package com.ShoppingBackend.User.DTO.RequestBody;

import java.util.List;
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
public class OrderDto {

	UUID userId;
	List<OrderProductDto>orderProductDto;
	
}
