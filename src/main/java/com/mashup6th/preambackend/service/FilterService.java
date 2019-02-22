package com.mashup6th.preambackend.service;

    import com.mashup6th.preambackend.dto.filter.FilterModel;
    import com.mashup6th.preambackend.entity.Filter;

    import java.util.List;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;

public interface FilterService {

    void save(String imgUrl, String email, FilterModel filterModel);

    Filter modify(String name, FilterModel filterModel);

    FilterModel getFilter(Long id);

//    boolean nameCheck(String name);

    List<FilterModel> getFilterList(String email);

    void delete(String name);
}
