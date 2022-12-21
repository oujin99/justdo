package com.yujeans.justdo.board;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.yujeans.justdo.user.Account;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Board {

	@Id @GeneratedValue
	private Long id;
	
	private String title;
	private String content;
	private String startDate;
	
	@Column(columnDefinition = "integer default 0", nullable = false)
	private int views;
	
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
