package com.yujeans.justdo.json;

import static org.hamcrest.CoreMatchers.containsString;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import oracle.sql.ARRAY;

public class CategoryParser {
	public static void main(String[] args) {
		try {
			Path relativePath = Paths.get("");
		      String path = relativePath.toAbsolutePath().toString();
		      System.out.println("Working Directory = " + path);
			Reader reader = new FileReader("src/main/resources/static/json/카테고리.json");
			
			int first = 1;
			int second = 1;
			int third = 1;
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			Iterator iter = jsonObject.keySet().iterator();
			while(iter.hasNext()) {
				String firstCategory = (String) iter.next();
//				System.out.println("INSERT INTO FIRST_CATEGORY VALUES(FIRST_CATEGORY_SEQ.nextval,'" + firstCategory + "');");
				
				JSONObject secondJsonObject = (JSONObject) jsonObject.get(firstCategory);
				Iterator secondIter = secondJsonObject.keySet().iterator();
				while(secondIter.hasNext()) {
					String secondCategory = (String) secondIter.next();
//					System.out.println("INSERT INTO SECOND_CATEGORY VALUES(SECOND_CATEGORY_SEQ.nextval, '" + secondCategory + "');");
					
					JSONArray jsonArray = (JSONArray)secondJsonObject.get(secondCategory);
					Iterator thirdIter = jsonArray.iterator();
					while(thirdIter.hasNext()) {
						String thirdCategory = (String) thirdIter.next();
//						System.out.println("INSERT INTO THIRD_CATEGORY VALUES(THIRD_CATEGORY_SEQ.nextval, '" + thirdCategory + "');");
						String sql = String.format("INSERT INTO CATEGORY VALUES(CATEGORY_SEQ.nextval, %d, %d, %d);", first, second, third);
						System.out.println(sql);
						third++;
					}
					second++;
				}
				first++;
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("ParseException");
			e.printStackTrace();
		}
	}
	
}
