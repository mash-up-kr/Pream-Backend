package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.category.CategoryInfo;
import com.mashup6th.preambackend.entity.Category;
import java.util.List;

public interface CategoryService {
  Boolean save(CategoryInfo categoryInfo);
  CategoryInfo modify(CategoryInfo categoryInfo);
  void delete(CategoryInfo categoryInfo);
  List<Category> getCategory(Long userId);
}
