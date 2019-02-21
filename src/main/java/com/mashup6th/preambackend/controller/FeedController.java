package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.filter.FeedFilterInfo;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.exception.NotFoundException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.FeedService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

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
      @ApiResponse(code = 404, message = "Not found by userEmail or filterId or pageNumber"),
      @ApiResponse(code = 500, message = "Server Error")})
  @ApiImplicitParams({
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N). Each page has 20 filters"),
  })
  @GetMapping("/lists")
  public @ResponseBody ApiResponseModel<Page<FeedFilterInfo>> apiGetFeed(
      @RequestParam String email,
      @RequestParam int page){
    ApiResponseModel<Page<FeedFilterInfo>> response = new ApiResponseModel<>();

    if (page < 0) {
      throw new NotFoundException ("해당 페이지 번호를 찾을 수 없습니다.");
    }

    int defaultFilterNum = 20;

    Pageable pageable = PageRequest.of(page, defaultFilterNum);
    if ( email.equals("")) {
      throw new BadRequestException("현재 로그인한 유저의 정보가 필요합니다.");
    }

    Page<FeedFilterInfo> feedFilterInfos = feedService.getFilterList(email, pageable);

    if (feedFilterInfos.getTotalPages() - 1 < page) {
      throw new NotFoundException("해당 페이지 번호를 찾을 수 없습니다.");
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
