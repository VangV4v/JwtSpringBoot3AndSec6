package com.vang.securitywithjwt.auth;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vang.securitywithjwt.dto.AccountDTO;
import com.vang.securitywithjwt.entities.Accounts;
import com.vang.securitywithjwt.repository.AccountRespository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRespository accountRespository;

	private ModelMapper mapper = new ModelMapper();

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Accounts accounts = accountRespository.findByUsername(username);
		if(null == accounts) {

			throw new UsernameNotFoundException("Username is not found");
		}
		AccountDTO dto = mapper.map(accounts, AccountDTO.class);
		return new User(dto.getUsername(), dto.getPassword(), dto.getListGrant());
	}

}