package com.mashup6th.preambackend.dto.category;

import com.mashup6th.preambackend.entity.Filter;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CategoryList {
  private String categoryName;
  private Filter filter;

}
