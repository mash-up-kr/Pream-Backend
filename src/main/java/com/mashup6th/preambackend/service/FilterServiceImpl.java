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
import sun.jvm.hotspot.debugger.Page;

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

//    @Override
//    public boolean nameCheck(String name) {
//        return filterRepository.findByName(name).isPresent();
//    }

    @Override
    public List<FilterModel> getFilterList(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found by userId"));
        List<FilterModel> filterModels = new ArrayList<>();

        //user_filter에서 user_id에 해당하는 filterid를 다 가져온다.
        //그 필터아이디로 필터를 검색해서 필터모델리스트에 넣어서 보내준다.
        List<UserFilter> userFilterList = userFilterRepository.findByUserIdHaveFilter(user.getId());

        log.info("filter서비스 임플" + userFilterList.size());

        for (UserFilter userFilter : userFilterList){
            Filter filter = filterRepository.findById(userFilter.getFilter().getId()).orElseThrow(()->new IllegalArgumentException("해당 filterId에 해당하는 필터가 존재하지 않습니다."));
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

    //filter테이블에 그 userId와 일치하는지 확인하여 일치한다면 그 필터를 삭제 -> 이 기능은 피드에 들어가야 함
    //userFilter에서 해당user아이디와 해당 filter아이디에 해당하는 필터를 지워주기.
    @Override
    public void delete(Long filterId, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("Not found by userId"));
        log.info("얻어온 유저의 이메일은");
        log.info(user.getEmail());

        UserFilter userFilter = userFilterRepository.findByUserIdAndFilterId(user.getId(), filterId);
        userFilterRepository.delete(userFilter);

    }



}
