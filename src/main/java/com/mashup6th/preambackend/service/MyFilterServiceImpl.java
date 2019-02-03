package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.persistence.MyFilterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFilterServiceImpl implements MyFilterService {

    private MyFilterRepository filterRepository;

    public MyFilterServiceImpl(MyFilterRepository filterRepository) {this.filterRepository = filterRepository;}

    @Override
    public void save(FilterModel filterCreateNew) {
        Filter filter = new Filter();
        filter.setName(filterCreateNew.getName());

        filterRepository.save(filter);
    }

    @Override
    public Filter modify(FilterModel filterModify) {
        Filter filter = filterRepository.findByName(filterModify.getName());
        filterRepository.save(filter);

        return filter;
    }

    @Override
    public List<Filter> getAll(Filter filter) {
        return filterRepository.findByAll(filter);
    }
}
