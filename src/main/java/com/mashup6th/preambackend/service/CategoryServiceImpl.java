package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.category.CategoryInfo;
import com.mashup6th.preambackend.entity.Category;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.entity.UserCategory;
import com.mashup6th.preambackend.persistence.CategoryRepository;
import com.mashup6th.preambackend.persistence.UserCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
  private CategoryRepository categoryRepository;
  private UserCategoryRepository userCategoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository, UserCategoryRepository userCategoryRepository) {
    this.categoryRepository = categoryRepository;
    this.userCategoryRepository = userCategoryRepository;
  }

  @Override
  public Boolean save(CategoryInfo categoryInfo) {
    // Category 테이블에 등록
    Category category = new Category();

    category.setName(categoryInfo.getName());
    category =  categoryRepository.save(category);

    // User_Category 관계테이블에 등록
    User user = new User();
    user.setId(categoryInfo.getUserId());

    UserCategory userCategory = new UserCategory();
    userCategory.setUser(user);
    userCategory.setCategory(category);
    userCategory = userCategoryRepository.save(userCategory);

    if ( (category != null && userCategory != null) ) {
      return true;
    }
    else {
      return  false; }
  }
}
