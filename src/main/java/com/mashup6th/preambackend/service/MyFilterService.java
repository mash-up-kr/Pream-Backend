package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.entity.Filter;

import java.util.List;

public interface MyFilterService {

    void save(Long userId, FilterModel filterModel);

    Filter modify(String name, FilterModel filterModel);

    FilterModel getFilter(Long id);

    boolean nameCheck(String name);

    List<Filter> getFilterList(String email);

    void delete(String name);
}
