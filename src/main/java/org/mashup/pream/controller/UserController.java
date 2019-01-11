package org.mashup.pream.controller;

import lombok.extern.slf4j.Slf4j;
import org.mashup.pream.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  //localhost:8088/user/signup

  /* 회원 가입 */
//  @PostMapping("/signup")
////  public void signUp(@RequestBody SignUpJson){
////
////  }

  /* 로그인 */

}
