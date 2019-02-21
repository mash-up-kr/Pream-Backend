package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.user.SignUpJson;
import com.mashup6th.preambackend.dto.user.UserLoginInfo;
import com.mashup6th.preambackend.entity.User;

public interface UserService {
  String emailCheck(String email);

  boolean nicknameCheck(String email);

  void save(SignUpJson signUpJson);

  User login(UserLoginInfo userLoginInfo);

  String sendEmail(String email);

  boolean checkLogin(String email);

  String getUserNickname(String email);

  }
