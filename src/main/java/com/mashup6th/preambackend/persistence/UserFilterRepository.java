package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.UserFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserFilterRepository extends JpaRepository<UserFilter, Long> {

    UserFilter save(UserFilter userFilter);

    UserFilter findByUserId(Long userId);

    List<UserFilter> findAllByUserId(Long userId);

    Optional<UserFilter> findById(Long filterId);

}
