package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.user.UserCheckNickname;
import com.mashup6th.preambackend.exception.AlreadyExistsException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.FeedService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {
  private FeedService feedService;

  public FeedController(FeedService feedService) {
    this.feedService = feedService;
  }

  /* 공유피드에서 피드를 받아오는 api */
  @GetMapping()
  public ApiResponseModel<List<Fi>> apiGetFeed(){

  }


//  @ApiResponses(value = {
//      @ApiResponse(code = 204, message = "Success"),
//      @ApiResponse(code = 409, message = "Already Exists"),
//      @ApiResponse(code = 500, message = "Failure")})
//  @ApiOperation(value = "apiCheckNickname", notes = "회원가입시 닉네임 중복시 에러")
//  @PostMapping("/signup/check/nickname/{nickname}")
//  public ApiResponseModel<UserCheckNickname> apiCheckNickname(@PathVariable String nickname){
//    ApiResponseModel<UserCheckNickname> response = new ApiResponseModel<>();
//
//    UserCheckNickname userCheckNickname =  new UserCheckNickname();
//
//    // 이메일이 중복되는지 검사
//    if(!userService.nicknameCheck(nickname)){
//      userCheckNickname.setNickname(nickname);
//    } else {
//      throw new AlreadyExistsException("회원가입시 입력한 닉네임이 중복됩니다.");
//    }
//
//    response.setStatusCode(HttpStatus.OK.value());
//    response.setMessage(HttpStatus.OK.toString());
//    response.setResult(userCheckNickname);
//
//    return response;
//  }


  /* 공유피드에 공유된 필터를 보여주는 api */

}
