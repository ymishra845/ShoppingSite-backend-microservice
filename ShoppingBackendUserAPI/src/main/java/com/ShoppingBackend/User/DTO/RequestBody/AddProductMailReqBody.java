package com.ShoppingBackend.User.DTO.RequestBody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductMailReqBody {

	String mailId;
	String mailMessage;
	String subjectLine;
	String userName;
	
	
}
