package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.UserCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
  public UserCategory save(UserCategory userCategory);
  public UserCategory findByCategoryId(Long categoryId);
  public List<UserCategory> findByUserId(Long userId);
  public UserCategory findByUserIdAndCategoryId(Long userId, Long categoryId);

}

