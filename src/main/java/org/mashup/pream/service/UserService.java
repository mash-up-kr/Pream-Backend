package org.mashup.pream.service;

import org.mashup.pream.dto.SignUpJson;
import org.mashup.pream.dto.user.UserLoginInfo;
import org.mashup.pream.entity.User;

public interface UserService {
  public boolean emailCheck(String email);
  public boolean nicknameCheck(String email);
  public void save(SignUpJson signUpJson);
  public User login(UserLoginInfo userLoginInfo);
}
