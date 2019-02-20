package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.filter.FeedFilterInfo;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.FeedService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
  @GetMapping("/lists")
  public ApiResponseModel<List<FeedFilterInfo>> apiGetFeed(@RequestParam String email){
    ApiResponseModel<List<FeedFilterInfo>> response = new ApiResponseModel<>();

    log.info(email);

    log.info("피드 컨트롤러");

    if ( email == null) {
      throw new BadRequestException("현재 로그인한 유저의 정보가 필요합니다.");
    }

    List<FeedFilterInfo> feedFilterInfos = feedService.getFilterList(email);

    for (FeedFilterInfo feedFilterInfo : feedFilterInfos){
      log.info(feedFilterInfo.getName());
      log.info("다운여부" + feedFilterInfo.getDownload());
    }

    return response;
  }


  /* 공유피드에 사용자가 필터를 다운받는 api */
  @PostMapping("/downlaod/fxilter/{filterId}")
  public ApiResponseModel<FeedFilterInfo> apiDownloadFilter(
      @RequestParam String email,
      @PathVariable Long filterId){
    ApiResponseModel<FeedFilterInfo> response = new ApiResponseModel<>();

    FeedFilterInfo feedFilterInfo = feedService.downloadFilter(email, filterId);

    log.info("다운받은 필터의 아이디와 이름은");
    log.info(feedFilterInfo.getId() +"/"+ feedFilterInfo.getName());

    return response;
  }


}
