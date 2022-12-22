package com.yujeans.justdo.category;

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
	name = "FirstCategory_seq_generator",
	sequenceName = "FirstCategory_seq",
	initialValue = 1,
	allocationSize = 1
)
public class FirstCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FirstCategory_seq_generator")
	private Long id;
	private String name;
}
