package com.yujeans.justdo.category.service;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yujeans.justdo.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	
	@Autowired
	private final CategoryRepository categoryRepository;
	
//	@Transactional(readOnly = true)
//	public List<Tuple> findSelectedCategory(String cateData){
//		return categoryRepository.findCategory(cateData);
//	}
//	
//	public List<Tuple> findThirdCategory(String sec_cateData){
//		return categoryRepository.findSecondCategory(sec_cateData);
//	}


}
