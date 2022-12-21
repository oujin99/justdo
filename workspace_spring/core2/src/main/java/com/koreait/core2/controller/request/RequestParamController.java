package com.koreait.core2.controller.request;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestParamController {

	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("username");
		int age = Integer.parseInt(req.getParameter("age"));

		System.out.println("user name = " + username);
		System.out.println("user age = " + age);

		resp.getWriter().write("ok");

	}

	/*
	 * @ResponseBody 어노테이션 정적 페이지에서만 실행가능, view 조회를 무시하고, HTTP message body 에 직접 해당
	 * 내용을 입력 ( return 방식으로 )
	 * 
	 * @RequestParam 파라미터 이름으로 바인딩 Request 를 통해 username, age 을 할당 받아
	 * 
	 * @ResponseBody 통해 HTTP message 에 출력
	 * 
	 */
	@ResponseBody
	@RequestMapping("/request-param-v2")
	public String requestParamV2(@RequestParam("username") String username, @RequestParam("age") int age)
			throws IOException {

		System.out.println("user name = " + username);
		System.out.println("user age = " + age);

		return "ok";
	}

	/*
	 * @RequestParam - HTTP 파라미터 이름이 변수 이름과 같으면
	 * 
	 * @RequestParam("변수이름") 이 생략가능
	 */

	@ResponseBody
	@RequestMapping("/request-param-v3")
	public String requestParamV3(@RequestParam String username, @RequestParam int age) throws IOException {

		System.out.println("user name = " + username);
		System.out.println("user age = " + age);

		return "ok";
	}

	/*
	 * 4. String, int 등의 단순 타입이라면 @RequestParam 생략 가능 MVC 내부에서 required = false 를
	 * 적용한다 ( 파라미터 필수 여부 )
	 */

	@ResponseBody
	@RequestMapping("/request-param-v4")
	public String requestParamV4(String username, int age) throws IOException {

		System.out.println("user name = " + username);
		System.out.println("user age = " + age);

		return "ok";
	}

	/*
	 * 5. required 유무  
	 */
	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequired(@RequestParam(required = true) String username,
			@RequestParam(required = false) Integer age) throws IOException {

		System.out.println("user name = " + username);
		System.out.println("user age = " + age);

		return "ok";
	}
	
	/* 
	 * 6. required + default 값 유무 
	 *  사용자가 입력하지 않을 때 default 값 설정하기 
	 */
	
	@ResponseBody
	@RequestMapping("/request-param-default")
	public String requestParamDefault(
			@RequestParam(required = true , defaultValue = "guest") String username,
			@RequestParam(required = false , defaultValue = "-1") Integer age) 
					throws IOException {

		System.out.println("user name = " + username);
		System.out.println("user age = " + age);

		return "ok";
	}
	
	/*
	 *  7. param-map
	 *  파라미터 로 받은 데이터 값을 
	 *  Map 데이터타입으로 받고 Map.get() 을통해 HTTP 에 출력하기 
	 */
	@ResponseBody
	@RequestMapping("/request-param-map")
	public String requestParamMap( @RequestParam Map<String, Object> paramMap)throws IOException {

		System.out.println("user name = " + paramMap.get("username"));
		System.out.println("user age = " + paramMap.get("age"));

		return "ok";
	}
	/*
	 * 8.
	 */
	
	@ResponseBody
	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1( @RequestParam String username,
			@RequestParam int age){

		HelloData hello = new HelloData();
		
		hello.setUsername(username);
		hello.setAge(age);
		
		System.out.println("username" + hello.getUsername());
		System.out.println("age" + hello.getAge());
		
		return "ok";
	}
	
	/*
	 * 9. @ModelAttribute 
	 * 		파라미터를 받아 필요한 객체를 만들고 그 객체에 값을 넣어주는 과정을 자동화
	 * 		
	 * 		url 파라미터에 값을 @ModelAttribute 가 자동으로 Setter 과정을 실행
	 * 		이후 개발자는 Getter 부분만 메꿔주면 끝 
	 * 
	 */
	
	@ResponseBody
	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(@ModelAttribute HelloData hellodata){

		
		System.out.println("username" + hellodata.getUsername());
		System.out.println("age" + hellodata.getAge());
		System.out.println("hello" + hellodata.toString());
		
		return "ok";
	}
	
	/*
	 *  10. @ModelAttribute 생략하기 
	 *  	String , int 등의 단순 데이터 타입 은 @RequestParam 에서도 생략가능하듯이
	 *  	단순 객체 또한 		@ModelAttribute 로도 생략이 가능하다 .
	 * 
	 */
	@ResponseBody
	@RequestMapping("/model-attribute-v3")
	public String modelAttributeV3(HelloData hellodata){

		
		System.out.println("username" + hellodata.getUsername());
		System.out.println("age" + hellodata.getAge());
		System.out.println("hello" + hellodata.toString());
		
		return "ok";
	}
}
