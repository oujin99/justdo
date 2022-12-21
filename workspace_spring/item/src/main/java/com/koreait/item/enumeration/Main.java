package com.koreait.item.enumeration;

public class Main {
	public static void main(String[] args) {
		Week today = Week.FRIDAY;
		
		System.out.println(today);
		Week.MONDAY.dayInfo();
		Week.TUSEDAY.dayInfo();
	}
}
