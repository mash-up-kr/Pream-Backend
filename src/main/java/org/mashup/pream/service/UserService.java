package org.mashup.pream.service;

import org.mashup.pream.dto.SignUpJson;

public interface UserService {
  public boolean emailCheck(String email);
  public boolean nicknameCheck(String email);
  public void save(SignUpJson signUpJson);
}
