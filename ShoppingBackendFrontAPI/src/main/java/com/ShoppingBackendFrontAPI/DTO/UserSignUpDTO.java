package com.ShoppingBackendFrontAPI.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSignUpDTO {

	String name;
	String email;
	String password;
	Long contactNumber;
	String Address;
	String type;
}
