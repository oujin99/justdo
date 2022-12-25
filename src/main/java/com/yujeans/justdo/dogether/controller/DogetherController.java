package com.yujeans.justdo.dogether.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yujeans.justdo.category.Category;
import com.yujeans.justdo.category.CategoryDto;
import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.category.SecondCategory;
import com.yujeans.justdo.category.ThirdCategory;
import com.yujeans.justdo.category.service.CategoryService;
import com.yujeans.justdo.credential.service.CredentialService;
import com.yujeans.justdo.dogether.AccountDogether;
import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.dogether.DogetherRegistDTO;
import com.yujeans.justdo.dogether.service.DogetherService;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.Credential;
import com.yujeans.justdo.user.service.AccountService;

import lombok.RequiredArgsConstructor;


@Controller
//@RequestMapping("/regist")
@RequiredArgsConstructor
public class DogetherController {
   
   @Autowired
   private final DogetherService dogetherService;
   private final CategoryService categoryService;
//   private final AccountService accountService;
   private final CredentialService credentialService;
   
   //메인 페이지에서 '두게더 등록' 클릭 시 두게더 등록 페이지로 이동
   @GetMapping("/dogether/registForm")
      public String dogetherRegistForm(HttpServletRequest request, Model model) {
//         System.out.println("nickname : "+request.getAttribute("nickname"));
//         System.out.println("id : "+request.getAttribute("id")); // 유저네임(회원가입할 때 적었던 아아디) -> 이걸로 db 조회
        
         // 대분류 조회 후 전달
         List<FirstCategory> firstCategory = dogetherService.selectFirstCategory();
         model.addAttribute("firstCategory", firstCategory);
         
//         //유저 정보 찾아오기
//         String username = String.valueOf(request.getAttribute("id"));
//         Account findUserInfo = accountService.findUserInfo(username);
//         
         
         // 빈 객체 전달
         Dogether dogether = new Dogether();
         model.addAttribute("dogether", dogether);
//         model.addAttribute("findUserInfo", findUserInfo);
         
         return "dogether/dogether_regist";
      }

   	// 인덱스 페이지에서 받은 소분류 카테고리를 이용해 두게더 썸네일 리스트 보여주기
    @GetMapping("/dogether/listForm/{thirdCategoryName}")
      public String dogetherListForm(@PathVariable("thirdCategoryName") String thirdCategoryName, Model model) {
    	
    	model.addAttribute("thirdCategoryName", thirdCategoryName);
    	
    	List<Dogether> dogetherFromThirdList = dogetherService.findDogetherByThirdCategoryName(thirdCategoryName);
    	
    	if(dogetherFromThirdList.size()>0) {
    		model.addAttribute("dogetherList", dogetherFromThirdList);
    	}
    	
        return "dogether/dogether_list";
    }

   //두번째 카테고리 가져오기(ajax)
   @ResponseBody
   @PostMapping("/dogether/firstcategory")
   public List<SecondCategory> secondCategory(@RequestBody CategoryDto selectedData, Model model) {
      
//      System.out.println("selectFirst::" + selectedData.getFirstData());
      
      //선택된 대분류 값 넘겨서 중분류 가져오기
      List<SecondCategory> secondCategoryList = categoryService.findSecondCategory(selectedData.getFirstData());

      model.addAttribute(secondCategoryList);
      

      return secondCategoryList;
   }
   
   //세번째 카테고리 가져오기(ajax)
      @ResponseBody
      @PostMapping("/dogether/secondcategory")
      public List<ThirdCategory> thirdCategory(@RequestBody CategoryDto selectedData, Model model) {
         
//         System.out.println("selectSecond::" + selectedData.getFirstData());
         
         //선택된 중분류 값 넘겨서 소분류 가져오기
         List<ThirdCategory> thirdCategoryList = categoryService.findThirdCategory(selectedData.getFirstData());

         model.addAttribute(thirdCategoryList);

         return thirdCategoryList;
      }

   // 작성된 두게더(클래스) 저장
   @PostMapping("/dogether/regist")
   public String saveDogether(@ModelAttribute DogetherRegistDTO dogetherForm, 
                        HttpServletRequest request,
                        RedirectAttributes redirectAttributes) {//@RequestParam("thirdCateSelect") String thirdCateSelect,
      
      String selectedThird = dogetherForm.getThirdCateSelect();

      //---------------테스트---------------------
//      System.out.println("닉네임 출력해보기 : " + request.getAttribute("nickname"));
//      System.out.println("아이디 출력해보기 : " + request.getAttribute("id"));
//
//      System.out.println("dto에서 가져온 세번째 카테고리 value 값 ::" + dogetherForm.getThirdCateSelect());
//      System.out.println("두게더 제목 : " + dogetherForm.getTitle());
      
//      System.out.println("requestParam에서 가져온 값 ::" + thirdCateSelect);
      //---------------테스트---------------------
      
      // 카테고리 아아디 가져오기 & Category 클래스의 id set
      Long categoryId = categoryService.findCategoryId(selectedThird);
//      System.out.println("****카테고리 아이디**** : " + categoryId);
      Category cate = new Category();
      cate.setId(categoryId);
//      System.out.println("****클래스 아이디**** : "+ cate.getId());
      
      /*
       *SELECT a.ID  FROM CREDENTIAL c 
   LEFT OUTER JOIN ACCOUNT a ON a.id = c.ACCOUNT_ID WHERE c.USERNAME = 'bbbb@naver.com'; 
       */

      
      // id 세팅
      //유저 정보 찾아오기
      String username = String.valueOf(request.getAttribute("id")); //회원가입할 때 쓰는 아이디
      Account findUserInfo = credentialService.findUserInfo(username); // account 테이블
//    		  accountService.findUserInfo(username); // account 테이블
      System.out.println("****찾아온 어카운트 아이디 ::: " + findUserInfo.getId());
      
      // 기존 account 아이디 세팅(일반 로그인 하기 전, 카카오로그인만 구현됐을 때)
//      Long accountId = Long.parseLong(String.valueOf(request.getAttribute("id"))); 
//      //request.getAttribute("id") : Object타입 -> String으로 형변환 -> Long으로 형변환
//      Account account = new Account();
//      account.setId(accountId);
//      System.out.println("**** 어카운트 아이디 **** : " + account.getId());
      
      Dogether dogether = new Dogether();
      dogether.setTitle(dogetherForm.getTitle());          // 제목
      dogether.setImage(dogetherForm.getImage());            // 두리더 이미지
      System.out.println("이미지 경로 :: "+dogetherForm.getImage());
      dogether.setLeaderInfo(dogetherForm.getLeaderInfo());   // 두리더 정보
      dogether.setSummary(dogetherForm.getSummary());         // 요약
      dogether.setRecommendTo(dogetherForm.getRecommendTo());   // 추천 대상
      dogether.setDetail(dogetherForm.getDetail());         // 상세설명
      dogether.setNotice(dogetherForm.getNotice());         // 공지사항
      dogether.setPrice(dogetherForm.getPrice());            // 가격
      dogether.setCategory(cate);                        // 카테고리 id
      dogether.setAccount(findUserInfo);
      
      //----카테고리 id 확인
      System.out.println("카테고리 설정한 id값 확인 ~~~ :: " + dogether.getCategory().getId());
      
      // 작성한 dogether 저장
      dogetherService.saveDogether(dogether);
      
      // 두게더 상세보기로 바로 넘어가기 위해 redirect로 dogether_id 넘기기
      Long dogetherSeq = dogetherService.selectDogetherId();
      
      //가져온 시퀀스값 확인(currval)
//      System.out.println("두게더 시퀀스 :::: " + dogetherSeq);
      
      return "redirect:/dogether/detail/"+dogetherSeq;
   }
   
   
   @GetMapping("/dogether/detail/{dogetherSeq}")
//   @RequestMapping(value = "/dogether/detail", method = RequestMethod.GET)
   public String dogetherDetail(@PathVariable("dogetherSeq")Long dogetherSeq, Model model){
      
      Dogether findDogether = dogetherService.findDogether(dogetherSeq);
      model.addAttribute("findDogether", findDogether);
      
      // 두리더 정보 찾아오기
      Account doleaderInfo = dogether.getAccount();
      System.out.println("디테일 페이지로 넘어온 후 두리더 이름 :::: " + doleaderInfo.getName());
      model.addAttribute("doleaderInfo", doleaderInfo);
      
      //강의 썸네일????
      
      //AccountDogether 테이블 select *
//      AccountDogether userId = dogetherService.findAccountDogether(dogetherId);
      
//      Account findAccount = dogetherService.findAccount(userId.getId());
//      model.addAttribute("findAccount", findAccount); // 두리더 사진 넣기 위함
//      
      
      return "dogether/dogether_detail";
   }
   

}
