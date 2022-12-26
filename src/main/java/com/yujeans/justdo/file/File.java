package com.yujeans.justdo.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@SequenceGenerator(
//		name = "FILE_SEQ_GENERATOR",
//		sequenceName = "FILE_SEQ"
//)
public class File {
	
	@Id
	@GeneratedValue
//	(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	// 업로드된 파일의 이름이 겹칠 수 있기 때문에 파일의 원본이름, 파일을 저장한 이름 따로 설정
	@Column(nullable = false)
	private String originFilename;
	@Column(nullable = false)
	private String storeFilename;
	
	@Enumerated(EnumType.STRING)
	private String filePath;

	
	
	
	//연관관계 xx
	
	@Builder
	public File(Long id, String originFilename, String storeFilename, String filePath) {
		super();
		this.id = id;
		this.originFilename = originFilename;
		this.storeFilename = storeFilename;
		this.filePath = filePath;
	}
	
	
	
	
}
