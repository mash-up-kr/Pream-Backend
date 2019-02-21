package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.filter.FeedFilterInfo;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.FeedService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/feed")
public class FeedController {
  private FeedService feedService;

  public FeedController(FeedService feedService) {
    this.feedService = feedService;
  }

  /* 공유피드에서 필터들을 보여주는 api */
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Not found by userEmail or filterId"),
      @ApiResponse(code = 500, message = "Server Error")})
  @GetMapping("/lists")
  public ApiResponseModel<List<FeedFilterInfo>> apiGetFeed(@RequestParam String email){
    ApiResponseModel<List<FeedFilterInfo>> response = new ApiResponseModel<>();
    if ( email.equals("")) {
      throw new BadRequestException("현재 로그인한 유저의 정보가 필요합니다.");
    }

    List<FeedFilterInfo> feedFilterInfos = feedService.getFilterList(email);

    for (FeedFilterInfo feedFilterInfo : feedFilterInfos){
      log.info(feedFilterInfo.getName());
      log.info("다운여부" + feedFilterInfo.getDownload());
    }

    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());
    response.setResult(feedFilterInfos);

    return response;
  }


  /* 공유피드에 사용자가 필터를 다운받는 api */
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 404, message = "Not found by userEmail or filterId"),
      @ApiResponse(code = 500, message = "Server Error")})
  @PostMapping("/downlaod/filter/{filterId}")
  public ApiResponseModel<FeedFilterInfo> apiDownloadFilter(
      @RequestParam String email,
      @PathVariable Long filterId){
    ApiResponseModel<FeedFilterInfo> response = new ApiResponseModel<>();

    if (email.equals("")) throw new BadRequestException("현재 로그인하고 있는 사용자의 email을 입력해 주세요");
    if (filterId == null) throw new BadRequestException("다운받을 필터의 id를 입력해 주세요");

    FeedFilterInfo feedFilterInfo = feedService.downloadFilter(email, filterId);

    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());
    response.setResult(feedFilterInfo);

    return response;
  }


}
