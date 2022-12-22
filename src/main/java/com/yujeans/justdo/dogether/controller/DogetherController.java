package com.yujeans.justdo.dogether.controller;

import java.util.List;

import javax.persistence.Tuple;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.category.SecondCategory;
import com.yujeans.justdo.category.ThirdCategory;
import com.yujeans.justdo.category.service.CategoryService;
import com.yujeans.justdo.dogether.AccountDogether;
import com.yujeans.justdo.dogether.Dogether;
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
//		List<SecondCategory> secondCategory = dogetherService.selectSecondCategory(null);
		List<ThirdCategory> thirdCategory = dogetherService.selectThirdCategory();
		
		model.addAttribute("firstCategory", firstCategory);
//		model.addAttribute("secondCategory", secondCategory);
		model.addAttribute("thirdCategory", thirdCategory);
		model.addAttribute("dogether", new Dogether());
		
		return "dogether/dogether_regist";
	}
	
	//두번째 카테고리 가져오기
	@ResponseBody
	@PostMapping("/firstcategory")
//	@RequestMapping(value = "firstcategory", method = { RequestMethod.POST })
	public List<SecondCategory> secondCategory(@RequestBody CategoryDto selectedData, Model model) {
		System.out.println("selectFirst::" + selectedData.getFirstData());
		List<SecondCategory> secondCategoryList = categoryService.findSecondCategory(selectedData.getFirstData());
		for(int i = 0; i < secondCategoryList.size(); i ++) {
			System.out.println("secondCategoryList입니다아아아아22 : " + secondCategoryList.get(i));
			System.out.println("----------------------");
		}
		model.addAttribute(secondCategoryList);

		//오류
//		for(SecondCategory t : secondCategoryList) {
//			System.out.println("t에 담아서 넣을 거지롱ㅋㅋ =" + t.getName() + t.getId());
//		}
		
		return secondCategoryList;
	}
	
	//세번째 카테고리 가져오기
		@ResponseBody
		@PostMapping("/secondcategory")
		public List<ThirdCategory> thirdCategory(@RequestBody CategoryDto selectedData, Model model) {
			System.out.println("selectSecond::" + selectedData.getFirstData());
			List<ThirdCategory> thirdCategoryList = categoryService.findThirdCategory(selectedData.getFirstData());
			for(int i = 0; i < thirdCategoryList.size(); i ++) {
				System.out.println("thirdCategoryList입니다아아아아33 : " + thirdCategoryList.get(i));
			}
			System.out.println("----------------------");
			model.addAttribute(thirdCategoryList);

			//오류
//			for(SecondCategory t : secondCategoryList) {
//				System.out.println("t에 담아서 넣을 거지롱ㅋㅋ =" + t.getName() + t.getId());
//			}
			
			return thirdCategoryList;
		}

	
//	@PostMapping
	public String saveDogether(Dogether dogetherForm,
								Model model) {
		
		Dogether dogether = new Dogether();
		dogether.setTitle(dogetherForm.getTitle());
		dogether.setImage(dogetherForm.getImage());
		dogether.setLeaderInfo(dogetherForm.getLeaderInfo());
		dogether.setSummary(dogetherForm.getSummary());
		dogether.setRecommendTo(dogetherForm.getRecommendTo());
		dogether.setNotice(dogetherForm.getRecommendTo());
		
//		dogether.getCategory().setId(dogetherForm.getCategory().getId());
		
		dogetherService.saveDogether(dogether);
		
		return "redirect:/{dogetherId}/detail(dogetherId=${dogether.id})";
	}
	
	
//	@GetMapping("/{dogetherId}/detail")
	public String dogetherDetail(@PathVariable("dogetherId")Long dogetherId, Model model){
		
		Dogether findDogether = dogetherService.findDogether(dogetherId);
		model.addAttribute("findDogether", findDogether);
		
		//AccountDogether 테이블 select *
		AccountDogether userId = dogetherService.findAccountDogether(dogetherId);
		
		Account findAccount = dogetherService.findAccount(userId.getId());
		model.addAttribute("findAccount", findAccount); // 두리더 사진 넣기 위함
		
		
		return "dogether/classDetail";
	}
	

}
