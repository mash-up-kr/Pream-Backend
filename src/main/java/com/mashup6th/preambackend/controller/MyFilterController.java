package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.entity.Filter;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.exception.NotFoundException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.MyFilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/api/v1/myfilter/*")
public class MyFilterController {

    private MyFilterService filterService;

    public MyFilterController(MyFilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping("/create/save")
    public ApiResponseModel<FilterModel> apiFilterCreate(@RequestBody FilterModel filterModel, BindingResult bindingResult) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterModel.setName(filterModel.getName());
        filterModel.setValue(filterModel.getValue());

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("필터 생성시 필요한 값이 모두 입력되지 않았습니다.");
        }

        filterService.save(filterModel);

        response.setStatusCode(HttpStatus.CREATED.value());

        return response;
    }

    @PutMapping("/create/modify")
    public ApiResponseModel<FilterModel> apiFilterModify(@RequestBody FilterModel filterModel, BindingResult bindingResult) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterModel.setName(filterModel.getName());
        filterModel.setValue(filterModel.getValue());

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("변경값을 제대로 입력해주세요.");
        }

        filterService.save(filterModel);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.toString());
        response.setResult(filterModel);

        return response;
    }

    @GetMapping("/filter/all")
    public ApiResponseModel<FilterModel> apiFilterGetAll(@RequestBody Filter filter, BindingResult bindingResult) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterService.findAll(filter);

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("");
        }

        response.setStatusCode(HttpStatus.OK.value());

        return response;
    }
}
