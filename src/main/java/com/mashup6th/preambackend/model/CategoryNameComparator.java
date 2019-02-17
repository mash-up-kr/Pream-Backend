package com.mashup6th.preambackend.model;

import com.mashup6th.preambackend.entity.Category;
import java.util.Comparator;

public class CategoryNameComparator implements Comparator<Category> {

  @Override
  public int compare(Category category1, Category category2) {

    String name1 = category1.getName();
    String name2 = category2.getName();

    return name1.compareToIgnoreCase(name2);
  }
}
