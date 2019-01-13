package org.mashup.pream.controller;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.mashup.pream.dto.SignUpJson;
import org.mashup.pream.exception.AlreadyExistsException;
import org.mashup.pream.exception.BadRequestException;
import org.mashup.pream.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  //localhost:8088/user/signup

  /* 회원 가입 */
  @PostMapping("/signup/save")
  public void signUp(@Valid @RequestBody SignUpJson signUpJson, BindingResult bindingResult){
    // 입력값이 모두 들어왔는지를 검사 -> BadRequestException
    if (bindingResult.hasErrors()){
      throw new BadRequestException("회원가입시 필요한 input 값을 모두 넣어주세요");
    }
    // 저장!
    userService.save(signUpJson);
  }

  /* 이메일 검사 */
  @PostMapping("/signup/check/email")
  public void checkEmail(String email){
    // 이메일이 중복되는지 검사
    if(userService.emailCheck(email){
      throw new AlreadyExistsException("회원가입시 입력한 이메일이 중복됩니디.");
    }


  }

  /* 닉네임 검사 */
  @PostMapping("/signup/check/nickname")
  public void checkNickname(String nickname){
    // 닉네임이 중복되는지 검사
    if(userService.nicknameCheck(nickname)){
      throw new AlreadyExistsException("회원가입시 입력한 닉네임이 중복됩니디.");
    }


  }


  /* 로그인 */

}
