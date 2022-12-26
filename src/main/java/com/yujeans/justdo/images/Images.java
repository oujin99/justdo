package com.yujeans.justdo.images;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Images {
	
	@Id
	@GeneratedValue
//	(strategy = GenerationType.IDENTITY)
//	@Column(name = "image_id")
	private Long id;
	
	// 업로드된 파일의 이름이 겹칠 수 있기 때문에 파일의 원본이름, 파일을 저장한 이름 따로 설정
//	@Column(nullable = false)
	private String orgNm;
//	@Column(nullable = false)
	private String savedNm;
	
//	@Enumerated(EnumType.STRING)
	private String savedPath;



	
	
	
	//연관관계 xx
	
	@Builder
	public Images(Long id, String orgNm, String savedNm, String savedPath) {
		super();
		this.id = id;
		this.orgNm = orgNm;
		this.savedNm = savedNm;
		this.savedPath = savedPath;
	}
	
	
	
	
}
