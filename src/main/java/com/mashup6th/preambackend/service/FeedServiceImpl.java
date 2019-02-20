package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FeedFilterInfo;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.persistence.FilterRepository;
import com.mashup6th.preambackend.persistence.UserFilterRepository;
import com.mashup6th.preambackend.persistence.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FeedServiceImpl implements FeedService {
  private FilterRepository filterRepository;
  private UserRepository userRepository;
  private UserFilterRepository userFilterRepository;

  public FeedServiceImpl(FilterRepository filterRepository,
      UserRepository userRepository) {
    this.filterRepository = filterRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<FeedFilterInfo> getFilterList(String email) {
    User user = userRepository.findByEmail(email);

    // 다운받을 때 마다도 이 로직 넣어주기
    // 유저가 본인의 창구에서 필터를 삭제할 때에도 이 로직 넣어주기

    // 1. 그 필터아이디가 유저필터의 유저의 필터아이디와 일치하는게있는지
    // 2. 유저의 필터를 모두 가져와서 - 이게 낫겠다. 그 중에서 그 필터와 일치하는게 있는지를 검사

    List<Filter> filters = filterRepository.findAllByOrderByRegDate();
    List<FeedFilterInfo> feedFilterInfos = new ArrayList<>();


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

//      if ( userFilterRepository.findUserFilterByUserHave(email) == null){
//        feedFilterInfo.setDownload(false);
//      } else {
//        feedFilterInfo.setDownload(true);
//      }

      feedFilterInfos.add(feedFilterInfo);
    }
    return feedFilterInfos;
  }
}
