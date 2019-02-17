package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.category.CategoryInfo;
import com.mashup6th.preambackend.dto.usercategory.UserCategoryInfo;
import com.mashup6th.preambackend.entity.Category;
import java.util.List;
import org.springframework.data.domain.Page;

public interface CategoryService {
  Boolean save(CategoryInfo categoryInfo);
  CategoryInfo modify(CategoryInfo categoryInfo);
  void delete(UserCategoryInfo userCategoryInfo);
  List<Category> getCategory(String email);
}
