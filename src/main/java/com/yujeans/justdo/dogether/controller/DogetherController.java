package com.yujeans.justdo.dogether.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yujeans.justdo.category.Category;
import com.yujeans.justdo.category.CategoryDto;
import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.category.SecondCategory;
import com.yujeans.justdo.category.ThirdCategory;
import com.yujeans.justdo.category.service.CategoryService;
import com.yujeans.justdo.dogether.AccountDogether;
import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.dogether.DogetherRegistDTO;
import com.yujeans.justdo.dogether.service.DogetherService;
import com.yujeans.justdo.images.Images;
import com.yujeans.justdo.images.service.ImageService;
import com.yujeans.justdo.user.Account;
import com.yujeans.justdo.user.Credential;
import com.yujeans.justdo.user.service.AccountDogetherService;
import com.yujeans.justdo.user.service.CredentialService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class DogetherController {
   
   @Autowired
   private final DogetherService dogetherService;
   @Autowired
   private final CategoryService categoryService;
//   private final AccountService accountService;
   @Autowired
   private final CredentialService credentialService;
   @Autowired
   private final AccountDogetherService accountDogetherService;
   
   private final ImageService imageService;
   
   //메인 페이지에서 '두게더 등록' 클릭 시 두게더 등록 페이지로 이동
   @GetMapping("/dogether/registForm")
      public String dogetherRegistForm(HttpServletRequest request, Model model) {
//         System.out.println("nickname : "+request.getAttribute("nickname"));
//         System.out.println("id : "+request.getAttribute("id")); // 유저네임(회원가입할 때 적었던 아아디) -> 이걸로 db 조회
        
         // 대분류 조회 후 전달
         List<FirstCategory> firstCategory = dogetherService.selectFirstCategory();
         model.addAttribute("firstCategory", firstCategory);
         
         // 빈 객체 전달
         Dogether dogether = new Dogether();
         model.addAttribute("dogether", dogether);
         
         //이미지 빈 객체 전달
         Images images = new Images();
         model.addAttribute("images", images);
//         model.addAttribute("findUserInfo", findUserInfo);
         
         return "dogether/dogether_regist";
      }

   	// 인덱스 페이지에서 받은 소분류 카테고리를 이용해 두게더 썸네일 리스트 보여주기
    @GetMapping("/dogether/listForm/{thirdCategoryName}")
      public String dogetherListForm(@PathVariable("thirdCategoryName") String thirdCategoryName, Model model) {
    	
    	model.addAttribute("thirdCategoryName", thirdCategoryName);
    	
    	List<Dogether> dogetherFromThirdList = dogetherService.findDogetherByThirdCategoryName(thirdCategoryName);
    	
    	System.out.println("두게더 사이즈 : "+dogetherFromThirdList.size());
    	
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
                        RedirectAttributes redirectAttributes,
                        @RequestParam("orgNm") MultipartFile orgNm
                       ) throws IOException {//@RequestParam("thirdCateSelect") String thirdCateSelect,
      
      String selectedThird = dogetherForm.getThirdCateSelect();

      
      // 카테고리 아아디 가져오기 & Category 클래스의 id set
      Long categoryId = categoryService.findCategoryId(selectedThird);
      Category cate = new Category();
      cate.setId(categoryId);
      
      
      // id 세팅
      //유저 정보 찾아오기
      String username = String.valueOf(request.getAttribute("id")); //회원가입할 때 쓰는 아이디
      Account findUserInfo = credentialService.findUserInfo(username); // account 테이블

      // 사진 저장---------------------------
      Long fileId = imageService.saveFile(orgNm);
      Images imageEntity = new Images();
      imageEntity.setId(fileId);
      
      
      
      Dogether dogether = new Dogether();
      dogether.setTitle(dogetherForm.getTitle());          // 제목
//      dogether.setImage(dogetherForm.getImage());            // 두리더 이미지
      dogether.setImages(imageEntity);
//fileService      dogether.setImage(dogetherForm.getImage());            // 두리더 이미지
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
      
//      redirectAttributes.addAttribute("dogether", dogether);
      // where 이미지 아이디로 가져와서 넘기기!
      
      //가져온 시퀀스값 확인(currval)
//      System.out.println("두게더 시퀀스 :::: " + dogetherSeq);
      
      redirectAttributes.addAttribute("dogetherSeq", dogetherSeq);
//      redirectAttributes.addAttribute("dogether", dogether); //저장된 두게더 넘기기(두리더 프로필 위해)
      
      return "redirect:/dogether/detail/" + dogetherSeq;
   }
   
   
   @GetMapping("/dogether/detail/{dogetherSeq}")
   public String dogetherDetail(@PathVariable("dogetherSeq")Long dogetherSeq,
//		   						@RequestParam("dogether")Dogether dogether,
		   						HttpServletRequest request,
		   						Model model) throws IOException{
      
	   
      Dogether findDogether = dogetherService.findDogether(dogetherSeq);
      model.addAttribute("findDogether", findDogether);
      
      // 두리더 정보 찾아오기
      Account doleaderInfo = findDogether.getAccount();
      System.out.println("디테일 페이지로 넘어온 후 두리더 이름 :::: " + doleaderInfo.getName());
      model.addAttribute("doleaderInfo", doleaderInfo);
      
      // 이 게시물이 현재 로그인 한 유저가 쓴건지 판단하기
      Credential credential = credentialService.findByAccountId(doleaderInfo.getId());
      
      String isCorrectId = "false";
      
      if(credential.getUsername().equals(request.getAttribute("id"))) {
    	  isCorrectId = "true";
      }
      
      model.addAttribute("isCorrectId", isCorrectId);
      model.addAttribute("dogetherId", dogetherSeq);
      
      Account account = credentialService.findUserInfo(request.getAttribute("id").toString());
      
      // account , dogether로 accountDogether가 있는지 확인 후 없으면 현재 계정으로 해당 두게더를 수강신청 하지 않은 상태
      AccountDogether ad = accountDogetherService.findByAccountAndDogether(account, findDogether);
      
      String isEnrolled = "false";
      if(ad!=null) {
    	  // ad가 null이 아니라면 현재 로그인 한 계정이 해당 두게더를 수강중인것
    	  isEnrolled = "true";
      }
      
      System.out.println("isEnrolled"+isEnrolled);
      
      model.addAttribute("isEnrolled", isEnrolled);
      
      // 사진 불러오기-------------------------
      Images fileInfo = imageService.getFileInfo(findDogether.getImages().getId()); // 파일 정보 찾기
      String path = "/dogether/" + fileInfo.getSavedNm(); // 파일의 공통 경로

      System.out.println("path : " + path);
      model.addAttribute("path", path);
      
//      StringBuilder sb = new StringBuilder(path); // 파일이 실제로 저장되어 있는 경로에
//      String fileName = fileInfo.getSavedNm();
//      sb.append(fileName); // 파일 이름 더하기
//      
//      URL fileUrl = new URL(sb.toString()); // file URL 생성
//      //-----------
//      InputStream imageStream = new FileInputStream(path);
//      
//      IOUtils.copy(fileUrl.openStream(), response.getOutputStream());
////      IOUtils.toByteArray(imageStream);
//      imageStream.close();
      //----------
      
      
      //----------------------------------
      try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return "dogether/dogether_detail";
   }
   
   @PostMapping("/dogether/search")
   public String dogetherSearch(@RequestParam("service_search") String requestText, Model model) {
	   
	   model.addAttribute("requestText", requestText);
	   
	   List<Dogether> dogetherList = dogetherService.findDogetherByTitle(requestText);
	   
	   if(dogetherList.size()>0) {
		   model.addAttribute("dogetherList", dogetherList);
	   }
	   
	   return "/dogether/dogether_search";
   }
   
   @GetMapping("/dogether/enroll/{dogetherId}")
   public String dogetherEnroll(@PathVariable("dogetherId") Long id, HttpServletRequest request, HttpServletResponse response) {
	   
	   Dogether dogether = dogetherService.findDogether(id);
	   Account account = credentialService.findUserInfo(request.getAttribute("id").toString());
	   
//	   System.out.println("두게더 타이틀 :"+dogether.getTitle());
//	   System.out.println("계정 이메일"+account.getEmail());
	   
	   //AccountDogether ad = accountDogetherService.findByAccountAndDogether(account, dogether);
	   
//	   if(ad==null) {
		   AccountDogether accountDogether = new AccountDogether();
		   accountDogether.setDogether(dogether);
		   accountDogether.setAccount(account);
		   accountDogetherService.save(accountDogether);
//	   }
	   
	   return "redirect:/user/mypage";
   }
   


   @GetMapping("/test/{id}")
   public void imgTest(@PathVariable(name = "id") Long id, HttpServletResponse response)throws IOException {
	// 사진 불러오기-------------------------
	      Images fileInfo = imageService.getFileInfo(1L); // 파일 정보 찾기
	      String path = "file://img/"; // 파일의 공통 경로
	      StringBuilder sb = new StringBuilder(path); // 파일이 실제로 저장되어 있는 경로에
	      String fileName = fileInfo.getSavedNm();
	      sb.append(fileName); // 파일 이름 더하기
	      System.out.println("hi : " + sb.toString());
	      URL fileUrl = new URL(sb.toString()); // file URL 생성
	      //-----------
	      InputStream imageStream = new FileInputStream(path);
	      System.out.println("fileUrl : " + fileUrl);
	      IOUtils.copy(fileUrl.openStream(), response.getOutputStream());
//	      IOUtils.toByteArray(imageStream);
	      imageStream.close();
   }
}
