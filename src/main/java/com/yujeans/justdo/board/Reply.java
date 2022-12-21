package com.yujeans.justdo.board;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.springframework.data.annotation.CreatedDate;
import com.yujeans.justdo.user.Account;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Reply {

	@Id @GeneratedValue
	private Long id;
	
	private String content;
	
	@CreatedDate
	private LocalDateTime startDate;
	
	@ManyToOne
	@JoinColumn
	private Account account;
	
	@ManyToOne
	@JoinColumn
	private Board board;
	

}
