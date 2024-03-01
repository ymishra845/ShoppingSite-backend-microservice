package com.ShoppingBackend.Email.DTO.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddProductDTO {

	String mailId;
	String mailMessage;
	String subjectLine;
	String userName;
	
}
