package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
  public UserCategory save(UserCategory userCategory);

}
