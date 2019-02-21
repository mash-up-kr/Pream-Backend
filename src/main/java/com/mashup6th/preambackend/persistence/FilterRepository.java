package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FilterRepository extends JpaRepository<Filter, Long> {

    Optional<Filter> findByName(String name);

    List<Filter> findAll();

    Optional<Filter> findById(Long filterId);

    Page<Filter> findAllByOrderByRegDate(Pageable pageable);

}
