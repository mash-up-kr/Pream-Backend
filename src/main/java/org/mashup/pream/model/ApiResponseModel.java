package org.mashup.pream.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApiResponseModel<T> {
  private int statusCode;
  private String message;
  private T result;
}
