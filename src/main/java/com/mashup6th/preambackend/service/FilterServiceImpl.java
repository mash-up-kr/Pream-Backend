package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.dto.user.UserCheckEmail;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.persistence.FilterRepository;
import com.mashup6th.preambackend.persistence.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {

    private FilterRepository filterRepository;

    private UserRepository userRepository;

    public FilterServiceImpl(
            FilterRepository filterRepository,
            UserRepository userRepository) {

        this.filterRepository = filterRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(String email, String imgUrl, FilterModel filterModel) {
        userRepository.findByEmail(email);

        Filter filter = new Filter();
        filter.setName(filterModel.getName());
        filter.setExposure(filterModel.getExposure());
        filter.setContrast(filterModel.getContrast());
        filter.setAdjust(filterModel.getAdjust());
        filter.setSharpen(filterModel.getSharpen());
        filter.setClarity(filterModel.getClarity());
        filter.setSaturation(filterModel.getSaturation());
        filter.setTone(filterModel.getTone());
        filter.setWhiteBalance(filterModel.getWhiteBalance());
        filter.setVignette(filterModel.getVignette());
        filter.setGrain(filterModel.getGrain());
        filter.setFade(filterModel.getFade());
        filter.setSplitTone(filterModel.getSplitTone());
        filter.setColorFilter(filterModel.getColorFilter());
        filter.setImgUrl(imgUrl);

        filterRepository.save(filter);
    }

    @Override
    public Filter modify(String name, FilterModel filterModify) {
        Filter filter = filterRepository.findByName(name).orElseThrow(()->new IllegalArgumentException("해당 이름으로 필터가 존재하지 않습니다."));
        filterRepository.save(filter);

        return filter;
    }

    @Override
    public FilterModel getFilter(String name) {
        Filter filter = filterRepository.findByName(name).orElseThrow(()->new IllegalArgumentException("해당 이름을 가진 필터가 존재하지 않습니다."));

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
        Filter filter = filterRepository.findByName(name).orElseThrow(()->new IllegalArgumentException("해당 이름의 필터가 존재하지 않습니다."));
        filterRepository.deleteById(filter.getId());
    }
}
