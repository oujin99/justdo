package com.yujeans.justdo.user.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.Credential;
import com.yujeans.justdo.user.dto.CredentialLoginRequestDto;
import com.yujeans.justdo.user.dto.SignUpFormDto;
import com.yujeans.justdo.user.service.AccountService;
import com.yujeans.justdo.user.service.CredentialService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AccountController {
	
	@Autowired
	private final AccountService accountService;
	
	@Autowired
	private final CredentialService credentialService;
	
	@GetMapping("/user/login")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/user/login")
	public String loginFormPostHandle(@ModelAttribute CredentialLoginRequestDto credentialLoginRequestDto) {
		System.out.println("loginFormPostHandle : " + credentialLoginRequestDto.getUsername());
		System.out.println("loginFormPostHandle : " + credentialLoginRequestDto.getPassword());
		try {
			URL url = new URL("http://localhost:9090/api/login");
			System.out.println("========LoginFormPostHandle3");
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//			urlConnection.setRequestProperty("username", credentialLoginRequestDto.getUsername());
//			urlConnection.setRequestProperty("password", credentialLoginRequestDto.getPassword());
//			urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
			urlConnection.setRequestProperty("Accept", "*/*");
			urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
			urlConnection.setRequestMethod("POST");
//			urlConnection.setDoInput(true);
			
			urlConnection.setDoOutput(true);
			
			String username = credentialLoginRequestDto.getUsername();
			String password = credentialLoginRequestDto.getPassword();
			String data = String.format("{ \"username\" : \"%s\", \"password\" : \"%s\" }", username, password);
			
			try(OutputStream os = urlConnection.getOutputStream()) {
				byte request_data[] = data.getBytes("utf-8");
				os.write(request_data);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int responseCode = urlConnection.getResponseCode();
			System.out.println("responseCode = " + responseCode);
			
			urlConnection.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            String res = "";
            while((line=br.readLine())!=null)
            {
                res+=line;
            }

            System.out.println("res = " + res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	@GetMapping("/user/signup")
	public String signupForm() {
		return "/user/signup";
	}
	
	@PostMapping("/user/signup")
	public String signUpFormSave(@ModelAttribute SignUpFormDto signUpFormDto) {
		Account account = new Account();
		account.setName(signUpFormDto.getName());
		account.setPhone(signUpFormDto.getPhone());
		accountService.save(account);
		
		Credential credential = new Credential();
		credential.setAccount(account);
		credential.setUsername(signUpFormDto.getEmail());
		credential.setPassword(signUpFormDto.getPassword());
		credential.setRoles(Arrays.asList("USER"));
		credential.setLoginMethod(credentialService.findLoginMethodType("basic"));
		credentialService.save(credential);
		
		
		return "/user/signup";
	}
	
	@GetMapping("/user/mypage")
	public String myPageForm() {
		return "/user/mypage";
	}
}
