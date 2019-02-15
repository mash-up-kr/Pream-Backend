package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);
  public User findByNickname(String nickname);
  //public User save(User user);
}
