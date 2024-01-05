package com.vang.securitywithjwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vang.securitywithjwt.entities.Accounts;
import com.vang.securitywithjwt.repository.AccountRespository;

@RestController
public class AccountController {

	@Autowired
	private AccountRespository accountRespository;

	@GetMapping("/accounts/")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Accounts>> getAlls() {

		return new ResponseEntity<List<Accounts>>(accountRespository.findAll(),HttpStatus.OK);
	}
}
