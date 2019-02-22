package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.entity.UserFilter;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.exception.NotFoundException;
import com.mashup6th.preambackend.persistence.FilterRepository;
import com.mashup6th.preambackend.persistence.UserFilterRepository;
import com.mashup6th.preambackend.persistence.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FilterServiceImpl implements FilterService {

    private FilterRepository filterRepository;

    private UserRepository userRepository;

    private UserFilterRepository userFilterRepository;

    public FilterServiceImpl(FilterRepository filterRepository,
        UserRepository userRepository,
        UserFilterRepository userFilterRepository) {
        this.filterRepository = filterRepository;
        this.userRepository = userRepository;
        this.userFilterRepository = userFilterRepository;
    }

    @Override
    public void save(String email, String imgUrl, FilterModel filterModel) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found by userId"));

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
        filter.setDescription(filterModel.getDescription());
        filter.setAdminYn(false);
        filter.setUser(user);

        Filter filter1 = filterRepository.save(filter);

        UserFilter userFilter = new UserFilter();
        userFilter.setUserId(user.getId());
        userFilter.setFilter(filter1);
        userFilter.setUseCount(1);

        userFilterRepository.save(userFilter);
    }

    @Override
    public FilterModel getFilter(Long id) {
        Filter filter = filterRepository.findById(id).orElseThrow(()->new NotFoundException("해당 이름을 가진 필터가 존재하지 않습니다."));

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
    public List<FilterModel> getFilterList(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found by userId"));
        List<FilterModel> filterModels = new ArrayList<>();

        List<UserFilter> userFilterList = userFilterRepository.findByUserIdHaveFilter(user.getId());

        for (UserFilter userFilter : userFilterList){
            Filter filter = filterRepository.findById(userFilter.getFilter().getId()).orElseThrow(()->new NotFoundException("해당 filterId에 해당하는 필터가 존재하지 않습니다."));
            FilterModel filterModel = new FilterModel();

            filterModel.setId(filter.getId());
            filterModel.setName(filter.getName());
            filterModel.setImgUrl(filter.getImgUrl());
            filterModel.setUseCount(1);

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

            filterModels.add(filterModel);
        }
        return filterModels;
    }

    @Override
    public void delete(Long filterId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("Not found by userId"));

        UserFilter userFilter = userFilterRepository.findByUserIdAndFilterId(user.getId(), filterId);

        if(userFilter == null) {
            throw new NotFoundException("현재 user는 해당 filter를 가지고있지 않습니다.");
        }

        userFilterRepository.delete(userFilter);
    }



}
