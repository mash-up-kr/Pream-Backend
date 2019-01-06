package org.mashup.pream.persistence;

import org.mashup.pream.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
