package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
