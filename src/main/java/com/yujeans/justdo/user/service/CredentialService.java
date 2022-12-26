package com.yujeans.justdo.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.repository.CredentialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredentialService {
	@Autowired
	private final CredentialRepository credentialRepository;
	
	public Account findByUsername(String username) {
		return credentialRepository.findByUsername(username);
	}
	
}
