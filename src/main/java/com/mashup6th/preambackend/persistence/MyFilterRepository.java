package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.Filter;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface MyFilterRepository extends JpaRepository<Filter, Long> {

    Filter findByName(String name);
    Filter findByCategory(List<String> category);
    Page<Filter> findByAll(Pageable pageable);
    List<Filter> findAll(Filter filter);

}
