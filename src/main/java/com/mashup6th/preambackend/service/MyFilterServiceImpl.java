package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.exception.NotFoundException;
import com.mashup6th.preambackend.persistence.MyFilterRepository;
import com.mashup6th.preambackend.persistence.UserRepository;
import netscape.security.ForbiddenTargetException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFilterServiceImpl implements MyFilterService {

    private MyFilterRepository filterRepository;

    private UserRepository userRepository;

    public MyFilterServiceImpl(MyFilterRepository filterRepository) {this.filterRepository = filterRepository;}

    @Override
    public void save(FilterModel filterCreateNew) {
        Filter filter = new Filter();
        filter.setName(filterCreateNew.getName());

        filterRepository.save(filter);
    }

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
    public List<Filter> getFilterList(Long userId) {
        userRepository.findById(userId).orElseThrow(()->new NotFoundException("No Found UserId"));
        return filterRepository.findAll();
    }

    @Override
    public void delete(Long filterId) {
        Filter filter = filterRepository.findById(filterId).orElseThrow(ForbiddenTargetException::new);
        filterRepository.deleteById(filter.getId());
    }
}
