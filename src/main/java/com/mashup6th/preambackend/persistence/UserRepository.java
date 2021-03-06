package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  public Optional<User> findByEmail(String email);
  public User findByNickname(String nickname);
  //public User save(User user);
}
