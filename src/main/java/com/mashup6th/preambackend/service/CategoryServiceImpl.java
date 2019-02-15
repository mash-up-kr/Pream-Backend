package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.category.CategoryInfo;
import com.mashup6th.preambackend.entity.Category;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.entity.UserCategory;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.exception.NotFoundException;
import com.mashup6th.preambackend.persistence.CategoryRepository;
import com.mashup6th.preambackend.persistence.UserCategoryRepository;
import com.mashup6th.preambackend.persistence.UserRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
  private CategoryRepository categoryRepository;
  private UserCategoryRepository userCategoryRepository;
  private UserRepository userRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository, UserCategoryRepository userCategoryRepository, UserRepository userRepository) {
    this.categoryRepository = categoryRepository;
    this.userCategoryRepository = userCategoryRepository;
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public Boolean save(CategoryInfo categoryInfo) {
    // category 테이블에 등록
    Category category = new Category();
    category.setName(categoryInfo.getName());
    category =  categoryRepository.save(category);

    // user_category 관계테이블에 등록
    User user = userRepository.findByEmail(categoryInfo.getEmail());

    UserCategory userCategory = new UserCategory();
    userCategory.setUserId(user.getId());
    userCategory.setCategory(category);
    userCategory = userCategoryRepository.save(userCategory);


    if ( (category != null && userCategory != null) ) {
      return true;
    }
    else {
      return  false; }
  }

  @Override
  @Transactional
  public CategoryInfo modify(CategoryInfo categoryInfo) {


    // 카테고리를 소유한 유저와 변경하려는 유저의 정보가 맞지 않는다면
    UserCategory userCategory = userCategoryRepository.findByCategoryId(categoryInfo.getCategoryId());
    if (userCategory == null){
      throw new NotFoundException("해당 id에 해당하는 카테고리가 존재하지 않습니다.");
    }

    User user = userRepository.getOne(userCategory.getUserId());

    if (!user.getEmail().equals(categoryInfo.getEmail())) {
      throw new BadRequestException("현재 유저가 이 카테고리명을 변경할 수 있는 권한이 없습니다.");
    }

    // category_id로 그 카테고리 객체를 불러오기
    Category category = categoryRepository.getOne(categoryInfo.getCategoryId());
    category.setName(categoryInfo.getName());

    // category 테이블에서 수정
    category = categoryRepository.save(category);
    categoryInfo.setName(category.getName());

    return categoryInfo;
  }

  @Override
  @Transactional
  public void delete(CategoryInfo categoryInfo) {
    User user = userRepository.findByEmail(categoryInfo.getEmail());
    if (user == null) {
      throw new NotFoundException("이 이메일로 등록된 user가 존재하지 않습니다.");
    }

    UserCategory userCategory = userCategoryRepository.findByUserIdAndCategoryId(user.getId(), categoryInfo.getCategoryId());
    if(userCategory == null) {
      throw new NotFoundException("현재 user는 해당 category를 소유하지 않습니다.");
    }

    userCategoryRepository.delete(userCategory);
  }

  @Override
  public List<Category> getCategory(Long userId) {
    // user_category테이블에서 현재 유저가 가진 category_id를 가져오기
    List<UserCategory> userCategories = userCategoryRepository.findByUserId(userId);
    List<Category> categories = new ArrayList<>();

    //받아온 category_id를 이용하여, category테이블에서 조회.
    for (UserCategory userCategory : userCategories){
      Category category = categoryRepository.getOne(userCategory.getCategory().getId());
      categories.add(category);
    }
    return categories;
  }
}
