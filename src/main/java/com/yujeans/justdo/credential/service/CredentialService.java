package com.yujeans.justdo.credential.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yujeans.justdo.credential.repository.CredentialRepository;
import com.yujeans.justdo.user.Account;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CredentialService {
	
	@Autowired
	private final CredentialRepository credentialRepository;
	
	public Account findUserInfo(String username) {
		return credentialRepository.findUserInfo(username);
	}
}
