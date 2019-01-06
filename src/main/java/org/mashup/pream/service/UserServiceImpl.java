package org.mashup.pream.service;

import lombok.extern.slf4j.Slf4j;
import org.mashup.pream.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
  private UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
