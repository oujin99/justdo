package com.yujeans.justdo.dogether.repository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yujeans.justdo.category.FirstCategory;
import com.yujeans.justdo.dogether.Dogether;
import com.yujeans.justdo.dogether.SimpleDogetherDto;
import com.yujeans.justdo.user.Account;


@Repository
public interface DogetherRepository extends JpaRepository<Dogether, Long>{
	
	// dogether 등록 폼에 넣을 카테고리
	@Query(value = "SELECT fc FROM FirstCategory fc")
	public List<FirstCategory> selectFirstCategory();
	
	// 두게더 상세보기로 바로 넘어가기 위해 redirect로 dogether_id 넘기기
	@Query(value = "SELECT Dogether_seq.CURRVAL FROM DUAL", nativeQuery = true)
	Long selectDogetherId();
	
	@Query(value = "SELECT d FROM Dogether d "
			+ "WHERE d.id = :id")
	public Dogether findDogether(@Param("id")Long id);

	@Query(value = "SELECT d FROM Dogether d "
			+ "	LEFT JOIN d.category c "
			+ "	LEFT JOIN c.firstCategory fc "
			+ "	LEFT JOIN c.secondCategory sc "
			+ "	LEFT JOIN c.thirdCategory tc "
			+ "WHERE tc.name = :thirdCategoryName")
	public List<Dogether> findDogetherByThirdCategoryName(@Param("thirdCategoryName")String thirdCategoryName);

	@Query(value = "SELECT d FROM Dogether d "
			+ "WHERE d.title LIKE %:requestText%")
	public List<Dogether> findDogetherByTitle(@Param("requestText") String requestText);

	@Query(value = "SELECT d FROM Dogether d WHERE d.account = :account")
	public List<Dogether> findDogetherByAccountId(@Param("account") Account account);
	
	@Query(value = "SELECT d FROM Dogether d "
			+ "	LEFT JOIN AccountDogether ad on d.id = ad.dogether "
			+ "WHERE ad.account = :account")
	public List<Dogether> findDogetherInfoByAccountIdOfAccountDogether(@Param("account")Account account);
	
}
