package com.yujeans.justdo.category.service;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.stereotype.Service;

import com.yujeans.justdo.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepostiory;
	
	public List<Tuple> findCategory(String name){
		return categoryRepostiory.findCategory(name);
	}
}
