package com.mashup6th.preambackend.controller;


import com.mashup6th.preambackend.dto.SignUpJson;
import com.mashup6th.preambackend.dto.user.UserCheckEmail;
import com.mashup6th.preambackend.dto.user.UserCheckNickname;
import com.mashup6th.preambackend.dto.user.UserLoginInfo;
import com.mashup6th.preambackend.exception.AlreadyExistsException;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.UserService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
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
  @ApiOperation(value = "apiCheckEmail",notes = "회원가입시 이메일 인증 / 중복시 에러")
  @PostMapping("/signup/check/{email}")
  public ApiResponseModel<UserCheckEmail> apiCheckEmail(@PathVariable String email){
    ApiResponseModel<UserCheckEmail> response = new ApiResponseModel<>();

    UserCheckEmail userCheckEmail =  new UserCheckEmail();

    log.info("체크체크");

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
    if(!userService.nicknameCheck(nickname)){
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
  public ApiResponseModel<SignUpJson> signUp(@Valid @RequestBody SignUpJson signUpJson, BindingResult bindingResult){
    ApiResponseModel<SignUpJson> response = new ApiResponseModel<>();

    signUpJson.setEmail(signUpJson.getEmail());
    signUpJson.setNickname(signUpJson.getNickname());
    signUpJson.setPassword(signUpJson.getPassword());
    // 입력값이 모두 들어왔는지를 검사 -> BadRequestException
    if (bindingResult.hasErrors()){
      throw new BadRequestException("회원가입시 필요한 input 값이 모두 입력되지 않았습니다.");
    }
    // 저장!
    userService.save(signUpJson);

    response.setStatusCode(HttpStatus.CREATED.value());
    response.setMessage(HttpStatus.CREATED.toString());
    response.setResult(signUpJson);

    return response;
  }

  /* 로그인 */
  @PostMapping("/login")
  public ApiResponseModel<UserLoginInfo> login(@Valid @RequestBody UserLoginInfo userLoginInfo, BindingResult bindingResult){
    ApiResponseModel<UserLoginInfo> response = new ApiResponseModel<>();

    if (bindingResult.hasErrors()) {
      throw new BadRequestException("로그인시 필요한 input 값이 모두 입력되지 않았습니다.");
    }

    if (userService.login(userLoginInfo) == null){
      throw new BadRequestException("로그인시 아이디 혹은 비밀번호가 잘못되었습니다.");
    }

    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());
    response.setResult(userLoginInfo);

    return response;
  }

}
