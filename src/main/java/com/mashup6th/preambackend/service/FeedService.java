package com.mashup6th.preambackend.service;

import com.mashup6th.preambackend.dto.filter.FeedFilterInfo;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeedService {
  public Page<FeedFilterInfo> getFilterList(String email, Pageable pageable);
  public FeedFilterInfo downloadFilter(String email, Long filterId);
}
