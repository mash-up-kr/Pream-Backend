package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.SignUpJson;
import com.mashup6th.preambackend.dto.user.UserLoginInfo;
import com.mashup6th.preambackend.entity.User;

public interface UserService {
  public String emailCheck(String email);
  public boolean nicknameCheck(String email);
  public void save(SignUpJson signUpJson);
  public User login(UserLoginInfo userLoginInfo);
  public String sendEmail(String email);

  }
