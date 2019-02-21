package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.UserFilter;
import java.util.List;
import org.hibernate.annotations.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserFilterRepository extends JpaRepository<UserFilter, Long> {
 // 그 유저가 가진 필터 중에서 해당필터아이디에 해당하는 것이 있는가?

  //돌아감
  @Query(value = "select us from UserFilter us where us.userId in (select u.id from User as u where u.email = (:email))")
  Page<UserFilter> findUserFilterByUserHave(@Param("email") String email, Pageable pageable);

/*  @Query(value = "select us.filterId from UserFilter us where us.userId in (select u.id from User as u where u.email = (:email))")
  List<Long> findUserFilterByUserHaveFilter(@Param("email") String email);*/

////
//  @Query(value = "select us from UserFilter us where us.userId in (select u.id from User as u where u.email = (:email)) AND us.filterId <> :filterId")
//  UserFilter findUserFilterByUserHaveThisFilter(@Param("email") String email, @Param("filterId") Long filterId);

  @Query(value = "select us from UserFilter us where us.userId=(:userId)")
  UserFilter findByUserIdHaveFilter(@Param("userId") Long userId);


//  // 주문번호로  주문한 회원 검색하기.
//  @Query(value = "select me from Member as me where me.id in (select distinct o.member from ORDERS as o where o.orderNumber = (:orderNumber)) order by me.id asc")
//   findMembersByOrderNumberInOrders(@Param("orderNumber") String orderNumber);
//
//  @Query(value = "select me from Member me where me.loginId=(:loginId)")
//  Member findByIdContaining(@Param("loginId") String loginId);

//SELECT filter_id from user_filter where user_id = (select id from user where user.email = ‘admin@naver.com’);


//  @Query(value = "select me from Member as me left join "
//      + "ORDERS as o on (me.id=o.member) where o.member in (select me.id from Member as me where me.name like concat('%', :name, '%')) group by me.id")
////  List<Member> findMembersByNameInOrders(@Param("name") String name);

//  UserFilter findBy
  public UserFilter save(UserFilter userFilter);
}
