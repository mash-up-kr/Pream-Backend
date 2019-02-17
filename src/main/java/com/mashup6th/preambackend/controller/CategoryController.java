package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.category.CategoryInfo;
import com.mashup6th.preambackend.dto.category.CategoryList;
import com.mashup6th.preambackend.dto.usercategory.UserCategoryInfo;
import com.mashup6th.preambackend.entity.Category;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Success"),
      @ApiResponse(code = 400, message = "user information or category name is not entered"),
      @ApiResponse(code = 500, message = "Failure")})
  @ApiOperation(value = "apiCategoryAdd", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 등록이 제대로 되지 않았다면 에러 / 사용자의 id를 전해주지 않았다면 에러")
  @PostMapping("/add")
  public ApiResponseModel<CategoryInfo> apiCategoryAdd(@Valid @RequestBody CategoryInfo categoryInfo){
    ApiResponseModel<CategoryInfo> response = new ApiResponseModel<>();

    if (categoryInfo.getName() == null){
      throw new BadRequestException("카테고리 명이 입력되지 않았습니다.");
    }

    if (categoryInfo.getEmail() == null){
      throw new BadRequestException("사용자의 email이 입력되지 않았습니다.");
    }

    if (categoryService.save(categoryInfo) == null){
      throw new BadRequestException("카테고리 명이 등록되지 않았습니다.");
    }

    response.setStatusCode(HttpStatus.CREATED.value());
    response.setMessage(HttpStatus.CREATED.toString());
    response.setResult(categoryInfo);

    return response;

  }

  /* 카테고리명 수정 api */
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Current user cannot modify this category name"),
      @ApiResponse(code = 404, message = "Current user does not have this category. Check the category Id"),
      @ApiResponse(code = 500, message = "Failure")})
  @ApiOperation(value = "apiCategoryModify", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 수정이 제대로 되지 않았다면 에러 / 사용자의 id를 전해주지 않았다면 에러")
  @PostMapping("/modify")
  public ApiResponseModel<CategoryInfo> apiCategoryModify(@Valid @RequestBody CategoryInfo categoryInfo){
    ApiResponseModel<CategoryInfo> response = new ApiResponseModel<>();

    if (categoryInfo.getName() == null){
      throw new BadRequestException("카테고리 명이 입력되지 않았습니다.");
    }

    if (categoryInfo.getEmail() == null){
      throw new BadRequestException("사용자의 id가 입력되지 않았습니다.");
    }

    if (categoryInfo.getCategoryId() == null){
      throw new BadRequestException("카테고리 id가 입력되지 않았습니다.");
    }

    categoryInfo = categoryService.modify(categoryInfo);

    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());
    response.setResult(categoryInfo);

    return response;
  }

  /* 카테고리명 삭제 -> 삭제시 사진은 삭제되지 않고 '모든사진' 카테고리로 */
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Current user cannot modify this category name"),
      @ApiResponse(code = 404, message = "Current user does not have this category. check the categoryId or userId"),
      @ApiResponse(code = 500, message = "Failure")})
  @ApiOperation(value = "apiCategoryDelete", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 수정이 제대로 되지 않았다면 에러 / 사용자의 id를 전해주지 않았다면 에러")
  @PostMapping("/delete")
  public ResponseEntity<ApiResponseModel> apiCategoryDelete(@Valid @RequestBody UserCategoryInfo userCategoryInfo) {
    ApiResponseModel response = new ApiResponseModel();

    if (userCategoryInfo.getEmail() == null){
      throw new BadRequestException("사용자의 id가 입력되지 않았습니다.");
    }

    if (userCategoryInfo.getCategoryId() == null){
      throw new BadRequestException("카테고리 id가 입력되지 않았습니다.");
    }

    categoryService.delete(userCategoryInfo);

    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());

    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  // 유저 카테고리의 그 카테고리를 가지는 컬럼을 함께 삭제.
  // 등록된 필터를 '모든사진' 카테고리로 옮겨주는 기능

  /* 카테고리 리스트를 가져오는 api */
  @ApiOperation(value = "apiCategoryGet", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 수정이 제대로 되지 않았다면 에러 / 사용자의 id를 전해주지 않았다면 에러")
  @GetMapping("/{email}")
  public ApiResponseModel<List<CategoryList>> apiCategoryGet(@PathVariable String email) {
    ApiResponseModel<List<CategoryList>> response = new ApiResponseModel<>();
    List<CategoryList> categoryLists = new ArrayList<>();


    log.info("유저아이디는" + email);
    List<Category> categories = categoryService.getCategory(email);

    for (Category category : categories){
      log.info("카테고리의 이름은" + category.getName());
    }

    //sort

    response.setStatusCode(HttpStatus.OK.value());
    response.setMessage(HttpStatus.OK.toString());
    response.setResult(categoryLists);


    return response;
  }
}
