package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.category.CategoryInfo;

public interface CategoryService {
  Boolean save(CategoryInfo categoryInfo);
  CategoryInfo modify(CategoryInfo categoryInfo);
  Boolean delete(Long userId, Long categoryId);
}
