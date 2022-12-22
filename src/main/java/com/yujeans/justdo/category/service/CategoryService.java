package com.yujeans.justdo.category.service;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.stereotype.Service;

import com.yujeans.justdo.category.SecondCategory;
import com.yujeans.justdo.category.ThirdCategory;
import com.yujeans.justdo.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepostiory;
	
	public List<SecondCategory> findSecondCategory(String selectFirst){
		return categoryRepostiory.findSecondCategory(selectFirst);
	}
	
	public List<ThirdCategory> findThirdCategory(String selectSecond){
		return categoryRepostiory.findThirdCategory(selectSecond);
	}
}
