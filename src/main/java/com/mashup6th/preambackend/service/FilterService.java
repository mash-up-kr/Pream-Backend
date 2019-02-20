package com.mashup6th.preambackend.service;

    import com.mashup6th.preambackend.dto.filter.FilterModel;
    import com.mashup6th.preambackend.entity.Filter;

    import java.util.List;

public interface FilterService {

    void save(String imgUrl, String email, FilterModel filterModel);

    Filter modify(String name, FilterModel filterModel);

    FilterModel getFilter(String name);

    boolean nameCheck(String name);

    List<Filter> getFilterList(String email);

//    void delete(String name);
}