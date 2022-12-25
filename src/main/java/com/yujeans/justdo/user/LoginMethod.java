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
@SequenceGenerator(
	name = "LoginMethod_seq_generator",
	sequenceName = "LoginMethod_seq",
	initialValue = 1,
	allocationSize = 1
)
public class LoginMethod {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LoginMethod_seq_generator")
	private Long id;
	private String type;
}
