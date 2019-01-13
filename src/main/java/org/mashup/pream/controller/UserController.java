package org.mashup.pream.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.mashup.pream.dto.SignUpJson;
import org.mashup.pream.dto.user.UserCheckEmail;
import org.mashup.pream.dto.user.UserCheckNickname;
import org.mashup.pream.exception.AlreadyExistsException;
import org.mashup.pream.exception.BadRequestException;
import org.mashup.pream.model.ApiResponseModel;
import org.mashup.pream.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserController {
      /*
     user 관련 API controller
     @PathVariable(GET) 과 acceptJson(!GET) 을 통해서 정보를 받아온다.
     받아오는 json 이 정확해야 한다.
     return : HttpStatus
      && APIResponse Class 를 Entity에 포함시켜 보낸다.
    */
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  //localhost:8088/user/signup

  /* 이메일 검사 */
  @PostMapping("/signup/check/{email}")
  public ApiResponseModel<UserCheckEmail> checkEmail(@PathVariable String email){
    ApiResponseModel<UserCheckEmail> response = new ApiResponseModel<>();

    UserCheckEmail userCheckEmail =  new UserCheckEmail();

    // 이메일이 중복되는지 검사
    if(!userService.emailCheck(email)){
      userCheckEmail.setEmail(email); //등록하려면 false값이 넘어와야함 . 즉 중복이 없을 때 false 값이 넘어와야함
      log.info("중복이없네요");
    } else {
      log.info("중복이있네요");
      throw new AlreadyExistsException("회원가입시 입력한 이메일이 중복됩니다.");
    }

    /* 이메일 인증 구현 예정*/


    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());
    response.setResult(userCheckEmail);

    return response;
  }

 /* 닉네임 검사 */
@PostMapping("/signup/check/{nickname}")
public ApiResponseModel<UserCheckNickname> checkNickname(@PathVariable String nickname){
  ApiResponseModel<UserCheckNickname> response = new ApiResponseModel<>();

  UserCheckNickname userCheckNickname =  new UserCheckNickname();

  // 이메일이 중복되는지 검사
  if(!userService.emailCheck(nickname)){
    userCheckNickname.setNickname(nickname); //등록하려면 false값이 넘어와야함 . 즉 중복이 없을 때 false 값이 넘어와야함
    log.info("중복이없네요");
  } else {
    log.info("중복이있네요");
    throw new AlreadyExistsException("회원가입시 입력한 닉네임이 중복됩니다.");
  }

  response.setStatusCode(HttpStatus.OK.value());
  response.setMessage(HttpStatus.OK.toString());
  response.setResult(userCheckNickname);

  return response;
}

  /* 회원 가입 */
  // SignUpJson값이 모두 null이 아니면(중복검사 및 정확한 input으로 들어왔다면) db에 저장해준다.
  @PostMapping("/signup/save")
  public void signUp(@Valid @RequestBody SignUpJson signUpJson, BindingResult bindingResult){
    signUpJson.setEmail("admin2@naver.com");
    signUpJson.setNickname("관리");
    signUpJson.setPassword("1234");
    // 입력값이 모두 들어왔는지를 검사 -> BadRequestException
    if (bindingResult.hasErrors()){
      throw new BadRequestException("회원가입시 필요한 input 값이 모두 입력되지 않았습니다.");
    }
    // 저장!
    userService.save(signUpJson);
  }

  /* 로그인 */

}
