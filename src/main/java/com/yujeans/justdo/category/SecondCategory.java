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
	name = "SecondCategory_seq_generator",
	sequenceName = "SecondCategory_seq",
	initialValue = 1,
	allocationSize = 1
)
public class SecondCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecondCategory_seq_generator")
	private Long id;
	private String name;
}
