package com.shoppingdatabase.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoppingdatabase.api.models.ACL;

@Repository
public interface ACLRepository extends JpaRepository<ACL, UUID> {

	@Query(value="select * from acl where requestor =:requestor and operation =:operation", nativeQuery = true)
	public ACL getConfiguration(String requestor, String operation);
}
