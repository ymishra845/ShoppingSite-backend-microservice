package com.shoppingdatabase.api.models;

import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//User -> one user can do man
//order -> many order can belong to single user -> Many to one


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	int quantity;
	double totalPrice;
	boolean isDelivered;
	@ManyToOne
	PortalUser user;
	
}
