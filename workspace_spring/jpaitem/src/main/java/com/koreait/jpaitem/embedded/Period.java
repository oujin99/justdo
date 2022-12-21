package com.koreait.jpaitem.embedded;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Period {

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
	// 테스트를 위한 파라미터가 있는 생성자
	public Period(LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	// 기본 생성자 
	public Period() {}
	
	
	
	
}
