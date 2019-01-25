//package org.mashup.pream.user;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mashup.pream.entity.User;
//import org.mashup.pream.persistence.UserRepository;
//import org.mashup.pream.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserTest {
//  @Autowired
//  UserRepository userRepository;
//  @Autowired
//  UserService userService;
//
//  @Test
//  public void User이메일중복검사() throws Exception{
//    //중복되면 return true;
//    if(userRepository.findByEmail("admin@naver.comm") != null)
//    System.out.println("중복이있네요");
//    else  System.out.println("중복이없네요");
//  }
//
//  @Test
//  public void User닉네임중복검사() throws Exception{
//    //중복되면 return true;
//    if(userRepository.findByNickname("관리자1") != null)
//      System.out.println("닉네임중복이있네요");
//    else  System.out.println("닉네임중복이없네요");
//  }
//
//  @Test
//  public void 회원가입save() throws Exception{
//    User user = new User();
//    user.setEmail("admin2@naver.com");
//    user.setNickname("관리자1");
//    user.setPassword("1234");
//
//    userRepository.save(user);
//  }
//
//}
