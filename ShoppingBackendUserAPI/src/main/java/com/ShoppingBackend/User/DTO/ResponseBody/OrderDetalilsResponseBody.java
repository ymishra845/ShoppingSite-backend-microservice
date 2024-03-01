package com.ShoppingBackend.User.DTO.ResponseBody;

import java.util.UUID;

import com.ShoppingBackend.User.DTO.GeneralDTO.UserDTO;

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
public class OrderDetalilsResponseBody {

	UUID id;
	int quantity;
	double totalPrice;
	boolean isDelivered;
}
