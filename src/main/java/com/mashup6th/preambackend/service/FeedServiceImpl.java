package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FeedFilterInfo;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.entity.UserFilter;
import com.mashup6th.preambackend.exception.NotFoundException;
import com.mashup6th.preambackend.persistence.FilterRepository;
import com.mashup6th.preambackend.persistence.UserFilterRepository;
import com.mashup6th.preambackend.persistence.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Null;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FeedServiceImpl implements FeedService {
  private FilterRepository filterRepository;
  private UserRepository userRepository;
  private UserFilterRepository userFilterRepository;

  public FeedServiceImpl(FilterRepository filterRepository,
      UserRepository userRepository,
      UserFilterRepository userFilterRepository) {
    this.filterRepository = filterRepository;
    this.userRepository = userRepository;
    this.userFilterRepository = userFilterRepository;
  }

  @Override
  @Transactional
  public List<FeedFilterInfo> getFilterList(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found by userId"));

    List<Filter> filters = filterRepository.findAllByOrderByRegDate();
    List<FeedFilterInfo> feedFilterInfos = new ArrayList<>();

    List<UserFilter> userFilters = userFilterRepository.findUserFilterByUserHave(email);


    for (Filter filter : filters){
      FeedFilterInfo feedFilterInfo = new FeedFilterInfo();

      feedFilterInfo.setId(filter.getId());

      feedFilterInfo.setName(filter.getName());
      feedFilterInfo.setDescription(filter.getName());
      feedFilterInfo.setImgUrl(filter.getImgUrl());

      feedFilterInfo.setAdjust(filter.getAdjust());
      feedFilterInfo.setClarity(filter.getClarity());
      feedFilterInfo.setColorFilter(filter.getColorFilter());
      feedFilterInfo.setContrast(filter.getContrast());
      feedFilterInfo.setExposure(filter.getExposure());
      feedFilterInfo.setFade(filter.getFade());
      feedFilterInfo.setGrain(filter.getGrain());
      feedFilterInfo.setSaturation(filter.getSaturation());
      feedFilterInfo.setSharpen(filter.getSharpen());
      feedFilterInfo.setSplitTone(filter.getSplitTone());
      feedFilterInfo.setTone(filter.getTone());
      feedFilterInfo.setVignette(filter.getVignette());
      feedFilterInfo.setWhiteBalance(filter.getWhiteBalance());
      feedFilterInfo.setDownload(false);

      for (UserFilter userFilter :userFilters) {
        if (userFilter.getFilter().getId().equals(filter.getId()))
          feedFilterInfo.setDownload(true);
      }

      feedFilterInfos.add(feedFilterInfo);
    }
    return feedFilterInfos;
  }

  //해당유저가 가진 userfilter의 필터아이디를 쭉 뽑아오든지 (이게맞음). 여기서 filter와 일치하느게 있는가를 보자userfilter리스트를 뽑아오던지해서,
  @Override
  @Transactional
  public FeedFilterInfo downloadFilter(String email, Long filterId) {
    log.info("서비스 임플");
    User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found by userId"));
    Filter filter = filterRepository.findById(filterId).orElseThrow(() -> new NotFoundException("Not found by filterId"));

    UserFilter userFilter = new UserFilter();
    userFilter.setUserId(user.getId());
    userFilter.setFilter(filter);
    userFilter.setUseCount(1);

    userFilterRepository.save(userFilter);


    FeedFilterInfo feedFilterInfo = new FeedFilterInfo();
    feedFilterInfo.setId(filter.getId());

    feedFilterInfo.setName(filter.getName());
    feedFilterInfo.setDescription(filter.getName());
    feedFilterInfo.setImgUrl(filter.getImgUrl());

    feedFilterInfo.setAdjust(filter.getAdjust());
    feedFilterInfo.setClarity(filter.getClarity());
    feedFilterInfo.setColorFilter(filter.getColorFilter());
    feedFilterInfo.setContrast(filter.getContrast());
    feedFilterInfo.setExposure(filter.getExposure());
    feedFilterInfo.setFade(filter.getFade());
    feedFilterInfo.setGrain(filter.getGrain());
    feedFilterInfo.setSaturation(filter.getSaturation());
    feedFilterInfo.setSharpen(filter.getSharpen());
    feedFilterInfo.setSplitTone(filter.getSplitTone());
    feedFilterInfo.setTone(filter.getTone());
    feedFilterInfo.setVignette(filter.getVignette());
    feedFilterInfo.setWhiteBalance(filter.getWhiteBalance());
    feedFilterInfo.setDownload(true);

    return feedFilterInfo;
  }
}
