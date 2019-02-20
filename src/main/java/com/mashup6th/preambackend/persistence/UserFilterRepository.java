package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.UserFilter;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserFilterRepository extends JpaRepository<UserFilter, Long> {
 // 그 유저가 가진 필터 중에서 해당필터아이디에 해당하는 것이 있는가?

//  @Query(value = "select us from UserFilter as us where us.user_id in (select u.id from USER as u where u.email = (:email))")
//  List<UserFilter> findUserFilterByUserHave(@Param("email") String email);

//SELECT filter_id from user_filter where user_id = (select id from user where user.email = ‘admin@naver.com’);


//  @Query(value = "select me from Member as me left join "
//      + "ORDERS as o on (me.id=o.member) where o.member in (select me.id from Member as me where me.name like concat('%', :name, '%')) group by me.id")
////  List<Member> findMembersByNameInOrders(@Param("name") String name);

//  UserFilter findBy
}
