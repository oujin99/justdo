package com.yujeans.justdo.dogether;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.yujeans.justdo.user.Account;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(
	name = "AccountDogether_seq_generator",
	sequenceName = "AccountDogether_seq",
	initialValue = 1,
	allocationSize = 1
)
public class AccountDogether {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AccountDogether_seq_generator")
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private Account account;
	
	@ManyToOne
	@JoinColumn
	private Dogether dogether;
}
