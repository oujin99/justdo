package com.yujeans.justdo.category;

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
	name = "Category_seq_generator",
	sequenceName = "Category_seq",
	initialValue = 1,
	allocationSize = 1
)
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Category_seq_generator")
	private Long id;
	
	@ManyToOne
	@JoinColumn
	private FirstCategory firstCategory;
	
	@ManyToOne
	@JoinColumn
	private SecondCategory secondCategory;
	
	@ManyToOne
	@JoinColumn
	private ThirdCategory thirdCategory;
	
}
