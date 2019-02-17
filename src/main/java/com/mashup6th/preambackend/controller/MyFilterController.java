package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.filter.FilterCheckName;
import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.exception.AlreadyExistsException;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.MyFilterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/myfilter")
public class MyFilterController {

    private MyFilterService filterService;

    public MyFilterController(MyFilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping
    public ApiResponseModel<FilterModel> apiCreateFilter(@RequestBody Long userId, FilterModel filterModel, BindingResult bindingResult) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        FilterCheckName filterCheckName = new FilterCheckName();

        // 필터 이름이 중복되는지 검사
        if (!filterService.nameCheck(filterModel.getName())) {
            filterCheckName.setName(filterModel.getName());
        } else {
            throw new AlreadyExistsException("Duplicate name");
        }

        filterModel.setName(filterModel.getName());
        filterModel.setExposure(filterModel.getExposure());
        filterModel.setContrast(filterModel.getContrast());
        filterModel.setAdjust(filterModel.getAdjust());
        filterModel.setSharpen(filterModel.getSharpen());
        filterModel.setClarity(filterModel.getClarity());
        filterModel.setSaturation(filterModel.getSaturation());
        filterModel.setTone(filterModel.getTone());
        filterModel.setWhiteBalance(filterModel.getWhiteBalance());
        filterModel.setVignette(filterModel.getVignette());
        filterModel.setGrain(filterModel.getGrain());
        filterModel.setFade(filterModel.getFade());
        filterModel.setSplitTone(filterModel.getSplitTone());
        filterModel.setColorFilter(filterModel.getColorFilter());

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("필터 생성시 필요한 값이 모두 입력되지 않았습니다.");
        }

        filterService.save(userId, filterModel);

        response.setStatusCode(HttpStatus.CREATED.value());

        return response;
    }

    @GetMapping
    public ApiResponseModel<FilterModel> apiGetFilter(@RequestBody Long id, BindingResult bindingResult) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterService.getFilter(id);

        response.setStatusCode(HttpStatus.OK.value());

        return response;
    }

    @PutMapping("{name}")
    public ApiResponseModel<FilterModel> apiModifyFilter(@PathVariable String name, @RequestBody FilterModel filterModel, BindingResult bindingResult) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterModel.setExposure(filterModel.getExposure());
        filterModel.setContrast(filterModel.getContrast());
        filterModel.setAdjust(filterModel.getAdjust());
        filterModel.setSharpen(filterModel.getSharpen());
        filterModel.setClarity(filterModel.getClarity());
        filterModel.setSaturation(filterModel.getSaturation());
        filterModel.setTone(filterModel.getTone());
        filterModel.setWhiteBalance(filterModel.getWhiteBalance());
        filterModel.setVignette(filterModel.getVignette());
        filterModel.setGrain(filterModel.getGrain());
        filterModel.setFade(filterModel.getFade());
        filterModel.setSplitTone(filterModel.getSplitTone());
        filterModel.setColorFilter(filterModel.getColorFilter());

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("변경값을 제대로 입력해주세요.");
        }

        filterService.modify(name, filterModel);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.toString());
        response.setResult(filterModel);

        return response;
    }

    @GetMapping("/list")
    public ApiResponseModel<FilterModel> apiGetFilters(@RequestBody String email, BindingResult bindingResult) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterService.getFilterList(email);

        if (bindingResult.hasErrors()) {
            throw new BadRequestException("");
        }

        response.setStatusCode(HttpStatus.OK.value());

        return response;
    }

    @DeleteMapping("{name}")
    public ApiResponseModel<FilterModel> apiDeleteFilter(@PathVariable String name) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterService.delete(name);
        response.setStatusCode(HttpStatus.NO_CONTENT.value());

        return response;
    }


}
