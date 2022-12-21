package com.koreait.item.enumeration;

public class Main_02 {
	public static void main(String[] args) {
		
		Season season = Season.SPRING;
		
		// name() : 열거 객체의 문자열 리턴
		String name = season.name();
		
		System.out.println(name);
		System.out.println("========");
		
		// 열거 객체의 index 를 호출하는 메소드 
		int ordinal = season.ordinal();
		System.out.println(ordinal);
		
		
		// 모든 열거 객체들을 배열로 만들어 리턴 
		Season[] seasons1 = Season.values();
		
		for ( Season s : seasons1 ) {
			System.out.println(s);
		}
		
	}
}
