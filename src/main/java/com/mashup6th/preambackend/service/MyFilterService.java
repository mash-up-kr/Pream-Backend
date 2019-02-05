package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.entity.Filter;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface MyFilterService {

    void save(FilterModel filterCreateNew);

    Filter modify(FilterModel filterModify);

    Page<Filter> getAll(Pageable pageable);

    List<Filter> findAll(Filter filter);
}