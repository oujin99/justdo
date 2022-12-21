package com.koreait.jpaitem.embedded;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@TableGenerator(name = "MEMBER_SEQ_GENERATOR",
				table = "MY_SEQUENCES",
				pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
@Getter @Setter
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,	
					generator = "MEMBER_SEQ_GENERATOR")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String username;
	
	private int age;
	
//	private LocalDateTime startDate;
//	private LocalDateTime endDate;
	
	// 기간 period
	// @Embedded와 @Embeddable 둘중에 하나마 넣어도 되나
	// 둘다 넣어줄것을 권장
	@Embedded
	private Period period;
	
	// Address
//	private String city;
//	private String street;
//	private String zipcode;
	
	// 주소
	@Embedded
	private Address address;
	
	// 회사 주소
	/*
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "city", column = @Column(name="WORK_CITY")),
		@AttributeOverride(name = "street", column = @Column(name="WORK_STREET")),
		@AttributeOverride(name = "zipcode", column = @Column(name="WORK_ZIPCODE"))})
	private Address workAddress;
	*/

}




















