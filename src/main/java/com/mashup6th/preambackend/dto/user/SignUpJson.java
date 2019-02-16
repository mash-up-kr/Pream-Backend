package com.mashup6th.preambackend.dto.user;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter //테스트용
@ToString
public class SignUpJson {



  //성별 (아마도 없음)
  //나이 (아마도 없음)

  //이메일
  @NotNull
  private String email;
  //패스워드
  @NotNull
  private String password;
  //
//  @NotNull
//  private String checkPassword;
  //닉네임
  @NotNull
  private String nickname;
}