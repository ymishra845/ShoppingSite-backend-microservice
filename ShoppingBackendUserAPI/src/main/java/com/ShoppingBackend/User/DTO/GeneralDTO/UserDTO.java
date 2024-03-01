package com.ShoppingBackend.User.DTO.GeneralDTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
	UUID id;
	String name;
	String email;
	String password;
	Long contactNumber;
	String address;
	String type;
}
