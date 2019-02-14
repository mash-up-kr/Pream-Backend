package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MyFilterRepository extends JpaRepository<Filter, Long> {

    Filter findByName(String name);

    List<Filter> findAll();

    Optional<Filter> findById(Long filterId);

}
