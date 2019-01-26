package com.mashup6th.preambackend.service;


import com.mashup6th.preambackend.dto.SignUpJson;
import com.mashup6th.preambackend.dto.user.UserLoginInfo;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.exception.NotFoundException;
import com.mashup6th.preambackend.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
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
    return userRepository.findByEmail(email) != null;
  }

  @Override
  public boolean nicknameCheck(String nickname) {
    //중복되면 return true
    return userRepository.findByNickname(nickname) != null;
  }

  @Override
  public void save(SignUpJson signUpJson) {
    User user = new User();
    user.setEmail(signUpJson.getEmail());
    user.setNickname(signUpJson.getNickname());
    user.setPassword(signUpJson.getPassword());

    userRepository.save(user);
  }

  @Override
  public User login(UserLoginInfo userLoginInfo) {
    User user = null;

    //이메일이 존재하지 않을 때
    if (userRepository.findByEmail(userLoginInfo.getEmail()) ==  null){
      throw new NotFoundException("해당 이메일에 해당하는 유저가 없습니다.");
    }

    //이메일이 존재하나 비밀번호가 맞지 않을 때
    user = userRepository.findByEmail(userLoginInfo.getEmail());

    if (! user.getPassword().equals(userLoginInfo.getPassword())) {
      log.info("비밀번호가 틀리네요 숙덕숙덕");
      return null;
    }

    return user;
  }
}
