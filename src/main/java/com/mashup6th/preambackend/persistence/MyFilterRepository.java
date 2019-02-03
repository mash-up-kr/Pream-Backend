package com.mashup6th.preambackend.persistence;

import com.mashup6th.preambackend.entity.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyFilterRepository extends JpaRepository<Filter, Long> {

    Filter findByName(String name);
    Filter findByCategory(List<String> category);
    List<Filter> findByAll(Filter filter);

}
