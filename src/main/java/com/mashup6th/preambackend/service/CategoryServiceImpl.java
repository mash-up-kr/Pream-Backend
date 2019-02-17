// //package com.mashup6th.preambackend.service;
// //
// //import com.mashup6th.preambackend.dto.category.CategoryInfo;
// //import com.mashup6th.preambackend.entity.Category;
// //import com.mashup6th.preambackend.entity.User;
// //import com.mashup6th.preambackend.entity.UserCategory;
// //import com.mashup6th.preambackend.persistence.CategoryRepository;
// //import com.mashup6th.preambackend.persistence.UserCategoryRepository;
// //import com.mashup6th.preambackend.persistence.UserRepository;
// <<<<<<< feature.filter
// =======
// //import java.time.LocalDateTime;
// >>>>>>> develop
// //import java.util.ArrayList;
// //import java.util.List;
// //import lombok.extern.slf4j.Slf4j;
// //import org.springframework.stereotype.Service;
// //
// //@Slf4j
// //@Service
// //public class CategoryServiceImpl implements CategoryService{
// //  private CategoryRepository categoryRepository;
// //  private UserCategoryRepository userCategoryRepository;
// //  private UserRepository userRepository;
// //
// //  public CategoryServiceImpl(CategoryRepository categoryRepository, UserCategoryRepository userCategoryRepository) {
// //    this.categoryRepository = categoryRepository;
// //    this.userCategoryRepository = userCategoryRepository;
// //  }
// //
// //  @Override
// //  public Boolean save(CategoryInfo categoryInfo) {
// //    // category 테이블에 등록
// //    Category category = new Category();
// //    category.setName(categoryInfo.getName());
// //    category =  categoryRepository.save(category);
// //
// //    // user_category 관계테이블에 등록
// //    User user = new User();
// //    user.setId(categoryInfo.getUserId());
// //
// //    UserCategory userCategory = new UserCategory();
// <<<<<<< feature.filter
// //    userCategory.setUserId(categoryInfo.getUserId());
// =======
// //    userCategory.setUser_id(categoryInfo.getUserId());
// >>>>>>> develop
// //    userCategory.setCategory(category);
// //    userCategory = userCategoryRepository.save(userCategory);
// //
// //    if ( (category != null && userCategory != null) ) {
// //      return true;
// //    }
// //    else {
// //      return  false; }
// //  }
// //
// //  @Override
// //  public CategoryInfo modify(CategoryInfo categoryInfo) {
// //    // category_id로 그 카테고리 객체를 불러오기
// //    Category category = categoryRepository.getOne(categoryInfo.getCategoryId());
// //    category.setName(categoryInfo.getName());
// //
// //    // category 테이블에서 수정
// //    category = categoryRepository.save(category);
// //    categoryInfo.setName(category.getName());
// //
// //    return categoryInfo;
// //  }
// //
// //  @Override
// //  public Boolean delete(Long userId, Long categoryId) {
// //    UserCategory userCategory = userCategoryRepository.findByUserIdAndCategoryId(userId, categoryId);;
// //    userCategoryRepository.delete(userCategory);
// //
// ////    Category category = categoryRepository.getOne(categoryId);
// ////    categoryRepository.delete(category);
// ////
// ////    log.info("카테고리의 아이디는" + categoryId);
// //
// ////    UserCategory userCategory = new UserCategory();
// ////    userCategory.setUser(user);
// ////    userCategory.setCategory(category);
// ////
// ////    userCategory = userCategoryRepository.findByCategoryId(categoryId);
// ////    log.info("유저카테고리의 아이디" + userCategory.getId());
// ////
// ////    userCategoryRepository.delete(userCategory);
// //
// //
// //
// //    return true;
// //  }
// //
// //  @Override
// //  public List<Category> getCategory(Long userId) {
// //    // user_category테이블에서 현재 유저가 가진 category_id를 가져오기
// //    List<UserCategory> userCategories = userCategoryRepository.findByUserId(userId);
// //    List<Category> categories = new ArrayList<>();
// //
// //    //받아온 category_id를 이용하여, category테이블에서 조회.
// //    for (UserCategory userCategory : userCategories){
// //      Category category = categoryRepository.getOne(userCategory.getCategory().getId());
// //      categories.add(category);
// //    }
// //    return categories;
// //  }
// //}
