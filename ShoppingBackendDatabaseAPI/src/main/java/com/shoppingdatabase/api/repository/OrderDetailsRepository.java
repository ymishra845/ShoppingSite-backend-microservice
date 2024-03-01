package com.shoppingdatabase.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shoppingdatabase.api.models.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, UUID>{

}
