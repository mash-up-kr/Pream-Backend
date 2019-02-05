package com.mashup6th.preambackend.dto.category;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class CategoryInfo {
  @NotNull
  private Long userId;

  private Long categoryId;

  @NotNull
  private String name;
}
