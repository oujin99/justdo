package com.koreait.jpaitem.embedded;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@TableGenerator( name = "MEMBER_SEQ_GENERATOR", table = "MY_SEQUENCES"
					, pkColumnValue = "MEMBER_SEQ" , allocationSize = 1)
@Getter @Setter
public class Member {

	@Id @GeneratedValue( strategy = GenerationType.TABLE,
			generator = "MEMBER_SEQ_GENERATOR")
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String username;
	
	/*
	 *  @Embedded 와 @Embeddable 둘 중 하나만 어노테이션해도 되나
	 *  	둘 다 선언하는 것을 권장 
	 */
	@Embedded
	private Period period;
	
	//private LocalDateTime startDate;
	//private LocalDateTime endDate;
	
	@Embedded
	private Address address;
	
//	private String city; 
//	private String street;
//	private String zipcode;
	
	// 회사 주소 ( 주소 2 )
//	@Embedded
//	@AttributeOverrides({
//			@AttributeOverride(name = "city" , column = @Column(name="WORK_CITY")),
//			@AttributeOverride(name = "street" , column = @Column(name="WORK_STREET")),
//			@AttributeOverride(name = "zipcode" , column = @Column(name="WORK_ZIPCODE"))
//	})
//	private Address workAddress;
	
	
	
}
