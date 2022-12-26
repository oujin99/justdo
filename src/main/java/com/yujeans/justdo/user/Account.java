package com.yujeans.justdo.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.yujeans.justdo.board.Board;
import com.yujeans.justdo.board.Reply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@SequenceGenerator(
	name = "Account_seq_generator",
	sequenceName = "Account_seq",
	initialValue = 1,
	allocationSize = 1
)
@ToString
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Account_seq_generator")
	@Column
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String image;
	private String profile;
	
	
	@OneToMany(mappedBy = "account")
	private List<Board> boards = new ArrayList<Board>();
	
	@OneToMany(mappedBy = "account")
	private List<Reply> replies = new ArrayList<Reply>();
	
	public void addBoard(Board board) {
		board.setAccount(this);
		this.boards.add(board);
	}
	public void addReply(Reply reply) {
		reply.setAccount(this);
		this.replies.add(reply);
	}	
		
}
