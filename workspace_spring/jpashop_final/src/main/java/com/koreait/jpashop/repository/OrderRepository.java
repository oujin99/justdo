package com.koreait.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.koreait.jpashop.domain.Order;
import com.koreait.jpashop.domain.OrderStatus;
import com.koreait.jpashop.domain.QMember;
import com.koreait.jpashop.domain.QOrder;
import com.koreait.jpashop.dto.OrderSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

	private final EntityManager em;
	
	// order 저장
	public void save(Order order) {
		em.persist(order);
	}
	
	// 단건 조회
	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}
	
	// 조회, queryDSL
	public List<Order> findAll(OrderSearch orderSearch){
		JPAQueryFactory query = new JPAQueryFactory(em);
		QOrder order = QOrder.order;
		QMember member = QMember.member;
		
		return query.select(order)
					.from(order)
					.join(order.member, member)// member : member의 알리아스
					//.where(order.orderStatus.eq(orderSearch.getOrderStatus()), member.name.like(orderSearch.getMemberName()))
					.where(statusEq(orderSearch.getOrderStatus()), nameLike(orderSearch.getMemberName()))
					.fetch()
					;
	}
	
	private BooleanExpression statusEq(OrderStatus orderStatus) {
		if( orderStatus == null ) {
			return null;
		}
		
		return QOrder.order.orderStatus.eq(orderStatus);
	}
	
	private BooleanExpression nameLike(String memberName) {
		if( memberName == null || memberName.equals("") ) {
			return null;
		}
		
		return QMember.member.name.like(memberName);
	}
	
	
	
	
	
	
	
	
	
}












