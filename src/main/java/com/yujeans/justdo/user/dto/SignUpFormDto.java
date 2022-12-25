package com.yujeans.justdo.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SignUpFormDto {
	@Email(message = "이메일 형식을 다시 확인해주세요.")
	@NotBlank(message = "이메일은 필수 사항 입니다.")
	private String email;
	
	@NotBlank(message = "패스워드는 필수 사항 입니다.")
	@Size(min=8, max=20, message = "패스워드의 길이는 8 ~ 20 까지 입력 가능합니다.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,20}", message="비밀 번호는 대소문자, 숫자, 특수 문자를 포함해야 합니다.")
	private String password;

	private String rePassword;
	
	@NotBlank(message = "사용자 이름은 필수 사항 입니다.")
	@Size(min=2, max=10, message="이름의 길이는 2 ~ 10 까지 입력 가능합니다.")
	private String name;
	
	@Pattern(regexp = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", message = "\"-(하이픈)\" 을 제외한 핸드폰 번호를 입력해주세요")
	private String phone;
}
