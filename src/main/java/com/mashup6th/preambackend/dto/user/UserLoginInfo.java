package com.mashup6th.preambackend.dto.user;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginInfo {
  @NotNull
  private String email;
  @NotNull
  private String password;
}
