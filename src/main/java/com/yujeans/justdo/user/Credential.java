package com.yujeans.justdo.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(
	name = "Credential_seq_generator",
	sequenceName = "Credential_seq",
	initialValue = 1,
	allocationSize = 1
)
public class Credential {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Credential_seq_generator")
	private Long id;
	private String username;
	private String password;
	
	@ManyToOne
	@JoinColumn
	private Account account;
	
	@ManyToOne
	@JoinColumn
	private LoginMethod loginMethod;
}
