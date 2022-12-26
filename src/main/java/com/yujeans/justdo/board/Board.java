package com.yujeans.justdo.board;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import com.yujeans.justdo.user.Account;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@SequenceGenerator(
		name = "Board_seq_generator",
		sequenceName = "Board_seq",
		initialValue = 1,
		allocationSize = 1
	)
@ToString
public class Board {

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Board_seq_generator")
	private Long id;
	
	private String title;
	private String content;
	private String startDate;
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private int views;
	
	@ManyToOne
	@JoinColumn
	@NotNull
	private Account account;
	
	@OneToMany(mappedBy = "board", cascade = {CascadeType.REMOVE})
    private List<Reply> replys = new ArrayList<Reply>();
	
	public void addReply(Reply reply) {
		reply.setBoard(this);
		this.replys.add(reply);
	}	
	
	
}
