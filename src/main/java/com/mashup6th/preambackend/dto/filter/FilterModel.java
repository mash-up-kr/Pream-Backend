package com.mashup6th.preambackend.dto.filter;

import com.mashup6th.preambackend.entity.Filter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FilterModel {

    private String name;

    // ToDo 추후 수정 필요
    private String value;

    private List<Filter> filters;
}
