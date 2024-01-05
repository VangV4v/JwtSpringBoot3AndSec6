package com.vang.securitywithjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vang.securitywithjwt.entities.Accounts;

public interface AccountRespository extends JpaRepository<Accounts, Integer>{

	@Query(value = "select * from accounts where username = ?1",nativeQuery = true)
	Accounts findByUsername(String username);
}