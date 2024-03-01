package com.shoppingdatabase.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoppingdatabase.api.models.PortalUser;

@Repository
public interface PortalUserRepo extends JpaRepository<PortalUser, UUID> {

}
