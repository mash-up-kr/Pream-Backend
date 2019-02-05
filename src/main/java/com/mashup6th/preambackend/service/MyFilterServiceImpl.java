package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.persistence.MyFilterRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

//    @Override
//    public Filter create(String name, String value) {
//        Filter filter = new Filter();
//        filter.setName(name);
//        filter.setValue(value);
//
//        filterRepository.save(filter);
//    }

    @Override
    public Filter modify(FilterModel filterModify) {
        Filter filter = filterRepository.findByName(filterModify.getName());
        filterRepository.save(filter);

        return filter;
    }

    @Override
    public Page<Filter> getAll(Pageable pageable) {
        return filterRepository.findByAll(pageable);
    }

    @Override
    public List<Filter> findAll(Filter filter) {
        return filterRepository.findAll(filter);
    }
}
