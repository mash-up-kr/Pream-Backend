package com.mashup6th.preambackend.dto.category;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CategoryInfo {
  private String email;

  private Long categoryId;

  private String name;
}
