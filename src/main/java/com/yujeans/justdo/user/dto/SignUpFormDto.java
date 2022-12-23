package com.yujeans.justdo.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpFormDto {
	private String email;
	private String password;
	private String name;
	private String phone;
}
