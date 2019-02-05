package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.category.CategoryAddInfo;
import com.mashup6th.preambackend.entity.Category;
import com.mashup6th.preambackend.persistence.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
  private CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

  @Override
  public Boolean save(CategoryAddInfo categoryAddInfo) {
    Category category = new Category();
    category.setName(categoryAddInfo.getName());

    if ( categoryRepository.save(category) != null) { return true; }
    else { return  false; }
  }
}
