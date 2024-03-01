package com.shoppingdatabase.api.models;

import java.util.UUID;


import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PortalUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	String name;
	String email;
	String password;
	Long contactNumber;
	String Address;
	String type;

}
