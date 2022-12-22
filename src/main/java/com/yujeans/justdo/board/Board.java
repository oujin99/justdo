package com.yujeans.justdo.board;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.yujeans.justdo.user.Account;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "Board_seq_generator",
		sequenceName = "Board_seq",
		initialValue = 1,
		allocationSize = 1
	)
public class Board {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Board_seq_generator")
	private Long id;
	
	private String title;
	private String content;
	private String startDate;
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private Long views;
	
	@ManyToOne
	@JoinColumn
	private Account account;
	
	@OneToMany(mappedBy = "board")
    private List<Reply> replys = new ArrayList<Reply>();
	
	public void addReply(Reply reply) {
		reply.setBoard(this);
		this.replys.add(reply);
	}	
	
	
}
