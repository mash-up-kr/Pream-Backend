//package com.mashup6th.preambackend.controller;
//
//import com.mashup6th.preambackend.dto.category.CategoryInfo;
//import com.mashup6th.preambackend.dto.category.CategoryList;
//import com.mashup6th.preambackend.entity.Category;
//import com.mashup6th.preambackend.exception.BadRequestException;
//import com.mashup6th.preambackend.model.ApiResponseModel;
//import com.mashup6th.preambackend.service.CategoryService;
//import io.swagger.annotations.ApiOperation;
//import java.util.ArrayList;
//import java.util.List;
//import javax.validation.Valid;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/v1/category/*")
//public class CategoryController {
//  private CategoryService categoryService;
//
//  public CategoryController(CategoryService categoryService) {
//    this.categoryService = categoryService;
//  }
//
//  /* 카테고리명 등록 api */
//  @ApiOperation(value = "apiCategoryAdd", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 등록이 제대로 되지 않았다면 에러 / 사용자의 id를 전해주지 않았다면 에러")
//  @PostMapping("/add")
//  public ApiResponseModel<CategoryInfo> apiCategoryAdd(@Valid @RequestBody CategoryInfo categoryInfo){
//    ApiResponseModel<CategoryInfo> response = new ApiResponseModel<>();
//
//    if (categoryInfo.getName() == null){
//      throw new BadRequestException("카테고리 명이 입력되지 않았습니다.");
//    }
//
//    if (categoryInfo.getUserId() == null){
//      throw new BadRequestException("사용자의 id가 입력되지 않았습니다.");
//    }
//
//    if (categoryService.save(categoryInfo) == null){
//      throw new BadRequestException("카테고리 명이 등록되지 않았습니다.");
//    }
//
//    response.setStatusCode(HttpStatus.CREATED.value());
//    response.setMessage(HttpStatus.CREATED.toString());
//    response.setResult(categoryInfo);
//
//    return response;
//
//  }
//
//  /* 카테고리명 수정 api */
//  @ApiOperation(value = "apiCategoryModify", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 수정이 제대로 되지 않았다면 에러 / 사용자의 id를 전해주지 않았다면 에러")
//  @PostMapping("/modify")
//  public ApiResponseModel<CategoryInfo> apiCategoryModify(@Valid @RequestBody CategoryInfo categoryInfo){
//    ApiResponseModel<CategoryInfo> response = new ApiResponseModel<>();
//
//    if (categoryInfo.getName() == null){
//      throw new BadRequestException("카테고리 명이 입력되지 않았습니다.");
//    }
//
//    if (categoryInfo.getUserId() == null){
//      throw new BadRequestException("사용자의 id가 입력되지 않았습니다.");
//    }
//
//    if (categoryInfo.getCategoryId() == null){
//      throw new BadRequestException("카테고리 id가 입력되지 않았습니다.");
//    }
//
//    categoryInfo = categoryService.modify(categoryInfo);
//
//    response.setStatusCode(HttpStatus.OK.value());
//    response.setMessage(HttpStatus.OK.toString());
//    response.setResult(categoryInfo);
//
//    return response;
//  }
//
//  /* 카테고리명 삭제 -> 삭제시 사진은 삭제되지 않고 '모든사진' 카테고리로 */
//  @ApiOperation(value = "apiCategoryDelete", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 수정이 제대로 되지 않았다면 에러 / 사용자의 id를 전해주지 않았다면 에러")
//  @PostMapping("/delete")
//  public ApiResponseModel<CategoryInfo> apiCategoryDelete(@Valid @RequestBody CategoryInfo categoryInfo) {
//    ApiResponseModel<CategoryInfo> response = new ApiResponseModel<>();
//
//    if (categoryInfo.getUserId() == null){
//      throw new BadRequestException("사용자의 id가 입력되지 않았습니다.");
//    }
//
//    if (categoryInfo.getCategoryId() == null){
//      throw new BadRequestException("카테고리 id가 입력되지 않았습니다.");
//    }
//
////    categoryService.delete( categoryInfo.getUserId(),categoryInfo.getCategoryId());
//
//    return response;
//  }
//
//
//  // 유저 카테고리의 그 카테고리를 가지는 컬럼을 함께 삭제.
//  // 등록된 필터를 '모든사진' 카테고리로 옮겨주는 기능
//
//  /* 카테고리 리스트를 가져오는 api */
////  @ApiOperation(value = "apiCategoryGet", notes = "카테고리 명이 입력되지 않았다면 에러 / 카테고리 명 수정이 제대로 되지 않았다면 에러 / 사용자의 id를 전해주지 않았다면 에러")
////  @GetMapping("/{userId}")
////  public ApiResponseModel<List<CategoryList>> apiCategoryGet(@PathVariable Long userId) {
////    ApiResponseModel<List<CategoryList>> response = new ApiResponseModel<>();
////    List<CategoryList> categoryLists = new ArrayList<>();
////    CategoryList categoryList =  new CategoryList();
////
////    log.info("유저아이디는" + userId);
////    List<Category> categories = categoryService.getCategory(userId);
////
////    for (Category category : categories){
////      log.info("카테고리의 이름은" + category.getName());
////      categoryList.setCategoryName(category.getName());
////      categoryLists.add(categoryList);
////    }
////
////    for (CategoryList cl : categoryLists){
////      log.info(cl.getCategoryName());
////    }
////
////    response.setStatusCode(HttpStatus.OK.value());
////    response.setMessage(HttpStatus.OK.toString());
////    response.setResult(categoryLists);
////
////
////    return response;
////  }
//
//
//    /* 즐겨찾기에 필터를 등록하는 api */
//
//}
