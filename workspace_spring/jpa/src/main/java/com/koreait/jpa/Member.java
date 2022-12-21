package com.koreait.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

/*
 * 객체와 테이블을 생성하고 매핑하기
 * 	@Entity : JPA가 관리할 객체
 *  @Id 	: 데이터베이스 PK와 매핑 
 *  
 *  create table Member(
 *  	id		number primary key,
 *  	name	varchar(200)
 *  );
 */

@Entity
@Getter @Setter
public class Member {
	
	@Id
	private Long id;
	
	@Column(unique = true, length = 10)
	private String name;
	
	// 컬럼명 지정
	@Column(name = "myage")
	private int age;
	
	// 날짜타입 매핑
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	// 날짜타입 매핑
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
	// 매핑 무시
	@Transient
	private int temp;
	
}











