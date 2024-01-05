package com.vang.securitywithjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vang.securitywithjwt.auth.JwtService;
import com.vang.securitywithjwt.dto.AuthRequestDTO;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/authenticate/")
	public ResponseEntity<String> authenticate(@RequestBody AuthRequestDTO dto) {

		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		if(authentication.isAuthenticated()) {

			return new ResponseEntity<String>(jwtService.generateToken(dto.getUsername()),HttpStatus.OK);
		}else {

			throw new UsernameNotFoundException("Login fail");
		}
	}
}