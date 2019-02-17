package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.entity.UserFilter;
import com.mashup6th.preambackend.exception.NotFoundException;
import com.mashup6th.preambackend.persistence.MyFilterRepository;
import com.mashup6th.preambackend.persistence.UserFilterRepository;
import com.mashup6th.preambackend.persistence.UserRepository;
import netscape.security.ForbiddenTargetException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyFilterServiceImpl implements MyFilterService {

    private MyFilterRepository filterRepository;

    private UserRepository userRepository;

    private UserFilterRepository userFilterRepository;

    public MyFilterServiceImpl(
            MyFilterRepository filterRepository,
            UserFilterRepository userFilterRepository,
            UserRepository userRepository) {

        this.filterRepository = filterRepository;
        this.userFilterRepository = userFilterRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Long userId, FilterModel filterModel) {
        Filter filter = new Filter();
        filter.setName(filterModel.getName());

        UserFilter userFilter = new UserFilter();
        userFilter.setUserId(userId);

        filterRepository.save(filter);
    }

    @Override
    public Filter modify(String name, FilterModel filterModify) {
        Filter filter = filterRepository.findByName(name).orElseThrow(()->new IllegalArgumentException("해당 이름으로 필터가 존재하지 않습니다."));
        filterRepository.save(filter);

        return filter;
    }

    @Override
    public FilterModel getFilter(Long id) {
        Filter filter = filterRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 이름을 가진 필터가 존재하지 않습니다."));

        FilterModel filterModel = new FilterModel();
        filterModel.setColorFilter(filter.getColorFilter());
        filterModel.setSplitTone(filter.getSplitTone());
        filterModel.setFade(filter.getFade());
        filterModel.setGrain(filter.getGrain());
        filterModel.setVignette(filter.getVignette());
        filterModel.setWhiteBalance(filter.getWhiteBalance());
        filterModel.setTone(filter.getTone());
        filterModel.setSaturation(filter.getSaturation());
        filterModel.setClarity(filter.getClarity());
        filterModel.setSharpen(filter.getSharpen());
        filterModel.setAdjust(filter.getAdjust());
        filterModel.setContrast(filter.getContrast());
        filterModel.setExposure(filter.getExposure());
        filterModel.setName(filter.getName());

        return filterModel;
    }

    @Override
    public boolean nameCheck(String name) {
        return filterRepository.findByName(name).isPresent();
    }

    @Override
    public List<Filter> getFilterList(String email) {
        userRepository.findByEmail(email);
        return filterRepository.findAll();
    }

    @Override
    public void delete(String name) {
        Filter filter = filterRepository.findByName(name).orElseThrow(ForbiddenTargetException::new);
        filterRepository.deleteById(filter.getId());
    }
}
