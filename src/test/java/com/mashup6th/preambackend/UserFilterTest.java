//package com.mashup6th.preambackend;
//
//import com.mashup6th.preambackend.entity.UserFilter;
//import com.mashup6th.preambackend.persistence.UserFilterRepository;
//import java.util.List;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@Transactional
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//public class UserFilterTest {
//
//  private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
//      .getLogger(UserFilterTest.class);
//
//  @Autowired
//  private UserFilterRepository userFilterRepository;
//
////  @Test
////  public void 테스트() throws Exception{
////    String email = "admin@naver.com";
//////    List<UserFilter> userFilters = userFilterRepository.findUserFilterByUserHave(email);
////
////    for (UserFilter userFilter : userFilters) {
////      log.info(userFilter.getId());
////    }
////  }
//
//}
