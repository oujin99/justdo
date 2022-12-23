package com.yujeans.justdo.dogether.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.persistence.Tuple;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yujeans.justdo.category.Category;
import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.category.SecondCategory;
import com.yujeans.justdo.category.ThirdCategory;
import com.yujeans.justdo.category.service.CategoryService;
import com.yujeans.justdo.dogether.AccountDogether;
import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.dogether.DogetherRegistDTO;
import com.yujeans.justdo.dogether.DogetherService;
import com.yujeans.justdo.user.Account;

import dto.CategoryDto;
import lombok.RequiredArgsConstructor;

//@GetMapping("/dogether/registForm")
//public String dogetherRegistForm() {
//	
//	return "dogether/dogether_regist";
//}


@Controller
//@RequestMapping("/regist")
@RequiredArgsConstructor
public class DogetherController {
	
	private final CategoryService categoryService;
	private final DogetherService dogetherService;
	
	@GetMapping("/regist")
	public String registForm(Model model) {
		
		List<FirstCategory> firstCategory = dogetherService.selectFirstCategory();
//		AccountDogether ad = new AccountDogether();
		
		model.addAttribute("firstCategory", firstCategory);
		model.addAttribute("dogether", new Dogether());
		
		
		return "dogether/dogether_regist";
	}
	
	//두번째 카테고리 가져오기
	@ResponseBody
	@PostMapping("/firstcategory")
	public List<SecondCategory> secondCategory(@RequestBody CategoryDto selectedData, Model model) {
		System.out.println("selectFirst::" + selectedData.getFirstData());
		List<SecondCategory> secondCategoryList = categoryService.findSecondCategory(selectedData.getFirstData());

		model.addAttribute(secondCategoryList);

		return secondCategoryList;
	}
	
	//세번째 카테고리 가져오기
		@ResponseBody
		@PostMapping("/secondcategory")
		public List<ThirdCategory> thirdCategory(@RequestBody CategoryDto selectedData, Model model) {
			System.out.println("selectSecond::" + selectedData.getFirstData());
			List<ThirdCategory> thirdCategoryList = categoryService.findThirdCategory(selectedData.getFirstData());

			model.addAttribute(thirdCategoryList);

			return thirdCategoryList;
		}

	
	@PostMapping("/regist/dogether")
	public String saveDogether(@ModelAttribute DogetherRegistDTO dogetherForm, Model model) {//@RequestParam("thirdCateSelect") String thirdCateSelect,
		
		//---------------테스트---------------------
		System.out.println("도착-----------------------**");
		System.out.println("dto에서 가져온 값 ::" + dogetherForm.getThirdCateSelect());
//		System.out.println("requestParam에서 가져온 값 ::" + thirdCateSelect);
		//---------------테스트---------------------
		
		String selectedThird = dogetherForm.getThirdCateSelect();
		
		System.out.println("제목 : " + dogetherForm.getTitle());

		// 카테고리 아아디 가져오기 & Category 클래스의 id set
		Long categoryId = categoryService.findCategoryId(selectedThird);
		Category cate = new Category();
		cate.setId(categoryId);
		
		Dogether dogether = new Dogether();
		dogether.setTitle(dogetherForm.getTitle()); 			// 제목
		dogether.setImage(dogetherForm.getImage());				// 두리더 이미지
		dogether.setLeaderInfo(dogetherForm.getLeaderInfo());	// 두리더 정보
		dogether.setSummary(dogetherForm.getSummary());			// 요약
		dogether.setRecommendTo(dogetherForm.getRecommendTo());	// 추천 대상
		dogether.setDetail(dogetherForm.getDetail());			// 상세설명
		dogether.setNotice(dogetherForm.getNotice());			// 공지사항
		dogether.setPrice(dogetherForm.getPrice());				// 가격
		dogether.setCategory(cate);								// 카테고리 id
		
		System.out.println("카테고리 설정한 id값 확인 ~~~ :: " + dogether.getCategory().getId());
		//----- 안됨 ---
		System.out.println("카테고리 설정한 firstCate 값 확인 ~~~ :: " + dogether.getCategory().getFirstCategory().getName());
		System.out.println("카테고리 설정한 secondCate 값 확인 ~~~ :: " + dogether.getCategory().getSecondCategory().getName());
		System.out.println("카테고리 설정한 thirdCate 값 확인 ~~~ :: " + dogether.getCategory().getThirdCategory().getName());
		//--- 안 됨---
		
		// 없는 부분 : accountId
		
		
//		dogetherService.saveDogether(dogether);
		
		return "redirect:/{dogetherId}/detail(dogetherId=${dogether.id})";
	}
	
	
	@GetMapping("/{dogetherId}/detail")
	public String dogetherDetail(@RequestParam("dogetherId")Long dogetherId, Model model){
		
		Dogether findDogether = dogetherService.findDogether(dogetherId);
		model.addAttribute("findDogether", findDogether);
		
		//AccountDogether 테이블 select *
		AccountDogether userId = dogetherService.findAccountDogether(dogetherId);
		
		Account findAccount = dogetherService.findAccount(userId.getId());
		model.addAttribute("findAccount", findAccount); // 두리더 사진 넣기 위함
		
		
		return "dogether/classDetail";
	}
	

}
