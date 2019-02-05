package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.category.CategoryInfo;
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
  @ApiOperation(value = "apiCategoryAdd", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 등록이 제대로 되지 않았다면 에러")
  @PostMapping("/add")
  public ApiResponseModel<CategoryInfo> apiCategoryAdd(@Valid @RequestBody CategoryInfo categoryInfo){
    ApiResponseModel<CategoryInfo> response = new ApiResponseModel<>();

    if (categoryInfo.getName() == null){
      throw new BadRequestException("카테고리 명이 입력되지 않았습니다.");
    }

    if (categoryInfo.getUserId() == null){
      throw new BadRequestException("사용자의 id가 입력되지 않았습니다.");
    }

    log.info("컨트롤러 userId " +  categoryInfo.getUserId());

    if (categoryService.save(categoryInfo) == null){
      throw new BadRequestException("카테고리 명이 등록되지 않았습니다.");
    }

    response.setStatusCode(HttpStatus.CREATED.value());
    response.setMessage(HttpStatus.CREATED.toString());
    response.setResult(categoryInfo);

    return response;

  }

  /* 카테고리명 수정 api */
  @ApiOperation(value = "apiCategoryModify", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 등록이 제대로 되지 않았다면 에러")
  @PostMapping("/modify/{name}")
  public ApiResponseModel<CategoryInfo> apiCategoryModify(@PathVariable String name){
    ApiResponseModel<CategoryInfo> response = new ApiResponseModel<>();
    CategoryInfo categoryInfo = new CategoryInfo();



    return response;
  }

  /* 카테고리명 삭제 -> 삭제시 사진은 삭제되지 않고 '모든사진' 카테고리로 */

  /* 카테고리 리스트를 가져오는 api */

  /* 즐겨찾기에 필터를 등록하는 api */

}