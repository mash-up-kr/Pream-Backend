package org.mashup.pream.service;

import lombok.extern.slf4j.Slf4j;
import org.mashup.pream.dto.SignUpJson;
import org.mashup.pream.entity.User;
import org.mashup.pream.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean emailCheck(String email) {
    //중복되면 return true
    if (userRepository.findByEmail(email) == null) {
      return false;
    }
      return true;
  }

  @Override
  public boolean nicknameCheck(String nickname) {
    //중복되면 return true
    if (userRepository.findByNickname(nickname) == null) {
      return false;
    }
    return true;
  }

  @Override
  public void save(SignUpJson signUpJson) {
    User user = new User();
    user.setEmail(signUpJson.getEmail());
    user.setNickname(signUpJson.getNickname());
    user.setPassword(signUpJson.getPassword());

    userRepository.save(user);
  }
}
