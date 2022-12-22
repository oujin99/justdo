package com.yujeans.justdo.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(
	name = "Account_seq_generator",
	sequenceName = "Account_seq",
	initialValue = 1,
	allocationSize = 1
)
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Account_seq_generator")
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String image;
}
