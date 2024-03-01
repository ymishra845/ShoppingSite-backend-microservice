package com.ShoppingBackendFrontAPI.DTO.requestDTO;

import java.util.List;
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
public class OrderDto {

	UUID Id;
	List<OrderProductDto> orderProductDto;
	
}
