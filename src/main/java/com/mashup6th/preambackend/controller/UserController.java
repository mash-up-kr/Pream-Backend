package com.mashup6th.preambackend.controller;


import com.mashup6th.preambackend.dto.user.SignUpJson;
import com.mashup6th.preambackend.dto.user.UserCheckNickname;
import com.mashup6th.preambackend.dto.user.UserEmailAuth;
import com.mashup6th.preambackend.dto.user.UserLoginInfo;
import com.mashup6th.preambackend.dto.user.UserLoginResult;
import com.mashup6th.preambackend.exception.AlreadyExistsException;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URLDecoder;
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
@RequestMapping("/api/v1/user/*")
public class UserController {
  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  /* 이메일 검사 */
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Success"),
      @ApiResponse(code = 409, message = "Already Exists"),
      @ApiResponse(code = 500, message = "Failure")})
  @ApiOperation(value = "apiCheckEmail", notes = "회원가입시 이메일 중복시 에러")
  @PostMapping("/signup/check/email/{email}")
  public ApiResponseModel<UserEmailAuth> apiCheckEmail(@PathVariable String email){
    ApiResponseModel<UserEmailAuth> response = new ApiResponseModel<>();

    UserEmailAuth userEmailAuth = new UserEmailAuth();
    String check = null;

    // 이메일이 중복되는지 검사
    try{
      check = URLDecoder.decode(userService.emailCheck(email), "utf-8");
    }catch (Exception e){
      e.printStackTrace();
    }

    if (check != null) {
      userEmailAuth.setAuthNumber(userService.sendEmail(email));
    } else throw new AlreadyExistsException("회원가입시 입력한 이메일이 중복됩니다.");

    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());
    response.setResult(userEmailAuth);

    return response;
  }

  /* 닉네임 검사 */
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Success"),
      @ApiResponse(code = 409, message = "Already Exists"),
      @ApiResponse(code = 500, message = "Failure")})
  @ApiOperation(value = "apiCheckNickname", notes = "회원가입시 닉네임 중복시 에러")
  @PostMapping("/signup/check/nickname/{nickname}")
  public ApiResponseModel<UserCheckNickname> apiCheckNickname(@PathVariable String nickname){
    ApiResponseModel<UserCheckNickname> response = new ApiResponseModel<>();

    UserCheckNickname userCheckNickname =  new UserCheckNickname();

    // 이메일이 중복되는지 검사
    if(!userService.nicknameCheck(nickname)){
      userCheckNickname.setNickname(nickname);
    } else {
      throw new AlreadyExistsException("회원가입시 입력한 닉네임이 중복됩니다.");
    }

    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());
    response.setResult(userCheckNickname);

    return response;
  }

  /* 회원 가입 */
  // SignUpJson값이 모두 null이 아니면(중복검사 및 정확한 input으로 들어왔다면) db에 저장해준다.
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Success"),
      @ApiResponse(code = 400, message = "Not all required values are entered."),
      @ApiResponse(code = 500, message = "Failure")})
  @ApiOperation(value = "apiSignUp", notes = "회원가입시 필요한 값이 모두 입력되지 않았다면 에러")
  @PostMapping("/signup/save")
  public ApiResponseModel<SignUpJson> apiSignUp(@Valid @RequestBody SignUpJson signUpJson, BindingResult bindingResult){
    ApiResponseModel<SignUpJson> response = new ApiResponseModel<>();

    signUpJson.setEmail(signUpJson.getEmail());
    signUpJson.setNickname(signUpJson.getNickname());
    signUpJson.setPassword(signUpJson.getPassword());
    // 입력값이 모두 들어왔는지를 검사 -> BadRequestException
    if (bindingResult.hasErrors()){
      throw new BadRequestException("회원가입시 필요한 값이 모두 입력되지 않았습니다.");
    }

    userService.save(signUpJson);

    response.setStatusCode(HttpStatus.CREATED.value());
    response.setMessage(HttpStatus.CREATED.toString());
    response.setResult(signUpJson);

    return response;
  }

  /* 로그인 */
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Success"),
      @ApiResponse(code = 400, message = "Email or password is incorrect"),
      @ApiResponse(code = 400, message = "Email or password is not entered"),
      @ApiResponse(code = 500, message = "Failure")})
  @ApiOperation(value = "apiLogin", notes = "로그인시 필요한 input이 모두 입력되지 않을 때 / 아이디 혹은 비밀번호가 잘못되었을 때 에러")
  @PostMapping("/login")
  public ApiResponseModel<UserLoginResult> apiLogin(@Valid @RequestBody UserLoginInfo userLoginInfo, BindingResult bindingResult){
    ApiResponseModel<UserLoginResult> response = new ApiResponseModel<>();

    if (bindingResult.hasErrors()) {
      throw new BadRequestException("로그인시 필요한 input 값이 모두 입력되지 않았습니다.");
    }

    if (userService.login(userLoginInfo) == null){
      throw new BadRequestException("로그인시 아이디 혹은 비밀번호가 잘못되었습니다.");
    }

    // 닉네임 구하기
    String userNickname = userService.getUserNickname(userLoginInfo.getEmail());

    UserLoginResult userLoginResult = new UserLoginResult();
    userLoginResult.setNickname(userNickname);


    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());
    response.setResult(userLoginResult);

    return response;
  }
}