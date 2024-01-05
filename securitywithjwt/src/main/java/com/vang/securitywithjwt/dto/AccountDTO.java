package com.vang.securitywithjwt.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Data;

@Data
public class AccountDTO {

	private int accountid;
	private String username;
	private String password;
	private String emails;
	private String roles;
	public List<GrantedAuthority> getListGrant() {

		List<GrantedAuthority> listGrant = new ArrayList<GrantedAuthority>();
		listGrant.add(new SimpleGrantedAuthority(roles));
		return listGrant;

	}

}