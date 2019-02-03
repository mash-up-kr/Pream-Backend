package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.SignUpJson;
import com.mashup6th.preambackend.dto.category.CategoryAddInfo;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/category/*")
public class CategoryController {

  /* 카테고리명 등록 api */
  // 1 ~ 99 번까지는 운영자가 등록할 수 있는 초기 카테고리 명
  // 100번 부터 사용자가 등록한 카테고리 명이 등록됨
  @ApiOperation(value = "apiCategoryAdd", notes = "회원가입시 필요한 값이 모두 입력되지 않았다면 에러")
  @PostMapping("/add")
  public ApiResponseModel<CategoryAddInfo> apiCategoryAdd(@Valid @RequestBody CategoryAddInfo categoryAddInfo, BindingResult bindingResult){
    ApiResponseModel<CategoryAddInfo> response = new ApiResponseModel<>();

    // 카테고리명 중복체크 (한 유저가 가지고있는 카테고리 내에서만)



    return response;
  }





  /* 카테고리명 수정 api */

  /* 카테고리명 삭제 -> 삭제시 사진은 삭제되지 않고 '모든사진' 카테고리로 */

}
