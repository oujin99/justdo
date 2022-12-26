package com.yujeans.justdo.board.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardFormDto {
	
	@NotBlank(message = "제목을 입력해 주세요")
	private String title;
	
	@NotBlank(message = "내용을 입력해 주세요")
	private String content;
}
