package com.yujeans.justdo.category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@SequenceGenerator(
	name = "SecondCategory_seq_generator",
	sequenceName = "SecondCategory_seq",
	initialValue = 1,
	allocationSize = 1
)
@ToString
public class SecondCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecondCategory_seq_generator")
	private Long id;
	private String name;
}
