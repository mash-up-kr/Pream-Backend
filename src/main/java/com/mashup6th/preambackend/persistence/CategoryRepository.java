package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  //public Category save(Category category);
//  public Category findByIdByNameDesc(Long id);
}
