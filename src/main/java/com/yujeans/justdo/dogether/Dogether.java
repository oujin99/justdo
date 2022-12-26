package com.yujeans.justdo.dogether;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.yujeans.justdo.category.Category;
import com.yujeans.justdo.images.Images;
import com.yujeans.justdo.user.Account;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@SequenceGenerator(
   name = "Dogether_seq_generator",
   sequenceName = "Dogether_seq",
   initialValue = 1,
   allocationSize = 1
)
public class Dogether {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Dogether_seq_generator")
	private Long id;
	private String title;
	private String image;
	
	
	
	private String leaderInfo;
	private String summary;
	private String recommendTo;
	private String detail;
	private String notice;
	private Integer price;
	
	@ManyToOne
	@JoinColumn
	private Category category;
	
	@ManyToOne
	@JoinColumn
	private Account account;
	
	@ManyToOne
	@JoinColumn
	private Images images;
}
