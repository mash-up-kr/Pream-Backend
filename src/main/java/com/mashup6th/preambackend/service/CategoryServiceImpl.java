package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.persistence.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
  private CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }
}
