package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FeedFilterInfo;
import java.util.List;

public interface FeedService {
  public List<FeedFilterInfo> getFilterList(String email);
  public FeedFilterInfo downloadFilter(String email, Long filterId);
}
