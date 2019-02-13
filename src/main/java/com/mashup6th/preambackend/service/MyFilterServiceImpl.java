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
    public Filter modify(String name, FilterModel filterModify) {
        Filter filter = filterRepository.findByName(name);
        filterRepository.save(filter);

        return filter;
    }

    @Override
    public boolean nameCheck(String name) {
        return filterRepository.findByName(name) != null;
    }

    @Override
    public List<Filter> findAll() {
        return filterRepository.findAll();
    }

    @Override
    public Filter delete(String name) {
        return filterRepository.deleteByName(name);
    }
}
