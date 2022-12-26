package com.yujeans.justdo.board;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.yujeans.justdo.user.Account;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "Reply_seq_generator",
		sequenceName = "Reply_seq",
		initialValue = 1,
		allocationSize = 1
	)
public class Reply {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Reply_seq_generator")
	private Long id;
	
	private String content;
	private String startDate;
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private Account account;
	
	@ManyToOne
	@JoinColumn
	private Board board;
	

}
