package com.yujeans.justdo.file.dto;

import com.yujeans.justdo.file.File;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {
	private Long id;
	private String originFilename;
	private String storeFilename;
	private String filePath;
	
	public File toEntity() {
		File build = File.builder()
					.id(id)
					.originFilename(originFilename)
					.storeFilename(storeFilename)
					.filePath(filePath)
					.build();
		
		return build;
	}
	
	@Builder
	public FileDto(Long id, String originFilename, String storeFilename, String filePath) {
		super();
		this.id = id;
		this.originFilename = originFilename;
		this.storeFilename = storeFilename;
		this.filePath = filePath;
	}
}




