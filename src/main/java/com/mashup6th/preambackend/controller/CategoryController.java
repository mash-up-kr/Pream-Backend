package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.category.CategoryAddInfo;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.CategoryService;
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
@RequestMapping("/api/v1/category/*")
public class CategoryController {
  private CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  /* 카테고리명 등록 api */
  @ApiOperation(value = "apiCategoryAdd", notes = "회원가입시 필요한 값이 모두 입력되지 않았다면 에러")
  @PostMapping("/add/{name}")
  public ApiResponseModel<CategoryAddInfo> apiCategoryAdd(@PathVariable String name){
    ApiResponseModel<CategoryAddInfo> response = new ApiResponseModel<>();

    CategoryAddInfo categoryAddInfo = new CategoryAddInfo();

    if (name == null){
      throw new BadRequestException("카테고리 명이 입력되지 않았습니다.");
    }

      categoryAddInfo.setName(name);

    if (categoryService.save(categoryAddInfo) == null){
      throw new BadRequestException("카테고리 명이 등록되지 않았습니다.");
    }

    response.setStatusCode(HttpStatus.CREATED.value());
    response.setMessage(HttpStatus.CREATED.toString());
    response.setResult(categoryAddInfo);

    return response;

  }





  /* 카테고리명 수정 api */

  /* 카테고리명 삭제 -> 삭제시 사진은 삭제되지 않고 '모든사진' 카테고리로 */

}
