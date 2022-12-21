package com.koreait.jpashop.test;

public class Main01 {

	public static void main(String[] args) {
		/*
		 *  파라미터 가변인자
		 *  
		 *  자바에서는 파라미터 개수가 다르면 다른 메소드로 인식 한다.
		 *  
		 *  // 파라미터가 한 개인 경우
		 *  public void search(String one){}
		 *  
		 *  // Map을 사용하는 경우
		 *  public void search(Map<String, String> param){}
		 *  
		 *  // List를 사용하는 경우
		 *  public void search(List<String> param){}
		 *  
		 *  // VO객채를 사용하는 경우
		 *  public void search(PramVO param){}
		 *  
		 *  [가변인자]
		 *   가변인자를 사용하면 동적으로 파라미터를 받을 수 있다.
		 *   사용법은 변수 타입 뒤에 기호(...)를 붙여주면 됩니다.
		 *   가변인자를 가지고 있는 메소드를 호출할 때의 방법
		 */
		
		test();
		
		test("A");
		
		test(1, "A");
		
		test("A", "B");
		
		test(5, new String[] {"A", "B", "C"});
		
	}
	
	public static void test(String... param) {
		System.out.println("test1번 실행");
		
		String[] array = param;
		for(String str:param) {
			System.out.println(str);
		}
		System.out.println("--------------");
	}
	
	// 다른 파라미터와 가변인자를 같이 사용하는 경우에는 가변인자를 제일 뒤에 위치시켜야 한다.
	public static void test(int num, String... param) {
		System.out.println("test2번 실행");
		System.out.println("num : " + num);
		
		String[] array = param;
		for(String str : param) {
			System.out.println(str);
		}
		
		System.out.println("--------------");
	}

}













