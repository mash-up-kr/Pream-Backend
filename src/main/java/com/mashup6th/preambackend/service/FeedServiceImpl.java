package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FeedFilterInfo;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.entity.UserFilter;
import com.mashup6th.preambackend.persistence.FilterRepository;
import com.mashup6th.preambackend.persistence.UserFilterRepository;
import com.mashup6th.preambackend.persistence.UserRepository;
import java.util.ArrayList;
import java.util.List;
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
  public List<FeedFilterInfo> getFilterList(String email) {
    User user = userRepository.findByEmail(email);


//    UserFilter userFilter = userFilterRepository.findByUserIdHaveFilter(3L);
//    log.info("레파지토리 테스트 ㅠㅠ");
//    log.info("그래서 유저아이디는 3이나와야함" +userFilter.getUserId());

    // 다운받을 때 마다도 이 로직 넣어주기
    // 유저가 본인의 창구에서 필터를 삭제할 때에도 이 로직 넣어주기

    // 1. 그 필터아이디가 유저필터의 유저의 필터아이디와 일치하는게있는지
    // 2. 유저의 필터를 모두 가져와서 - 이게 낫겠다. 그 중에서 그 필터와 일치하는게 있는지를 검사

    //유저필터에 그 필터아이디와 유저아이디에 해당하는 컬럼이 있다면
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
    log.info("email / filterId" + email + filterId);



    User user = userRepository.findByEmail(email);
    Filter filter = filterRepository.getOne(filterId);

    log.info("user" + user);
    log.info("filter" + filter);

    UserFilter userFilter = new UserFilter();
    log.info("userfilter" + userFilter);
    userFilter.setUserId(user.getId());
    userFilter.setFilter(filter);
    userFilter.setUseCount(1);


    log.info("userfilter filtername" + userFilter.getFilter().getName());
    userFilter = userFilterRepository.save(userFilter);


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

    return  feedFilterInfo;
  }
}
