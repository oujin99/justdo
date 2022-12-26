package com.yujeans.justdo.board.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyFormDto {
	
		@NotBlank(message = "댓글을 입력해 주세요")
		private String content;
}
