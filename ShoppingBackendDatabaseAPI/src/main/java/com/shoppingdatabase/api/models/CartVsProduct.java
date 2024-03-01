package com.shoppingdatabase.api.models;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartVsProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	UUID id;
	UUID cartId;
	UUID productId;
	int totalPrice;
	int totalQuantity;
}
