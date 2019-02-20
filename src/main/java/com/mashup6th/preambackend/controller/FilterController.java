package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.filter.FilterCheckName;
import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.exception.AlreadyExistsException;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.FilterService;
import com.mashup6th.preambackend.service.StorageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/api/v1/filter")
public class FilterController {

    private final StorageService storageService;

    private FilterService filterService;

    public FilterController(
            FilterService filterService,
            StorageService storageService) {
        this.filterService = filterService;
        this.storageService = storageService;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = FilterModel.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Failure")})
    @ApiOperation(value = "apiCreateFilter", notes = "필터 이름 중복시 에러")
    @RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ApiResponseModel<FilterModel> apiCreateFilter(@RequestParam(value = "image") MultipartFile image,
                                                         @RequestParam(value = "email") String email,
                                                         @RequestParam(value = "name") String name,
                                                         @RequestParam(value = "exposure") Float exposure,
                                                         @RequestParam(value = "contrast") Float contrast,
                                                         @RequestParam(value = "adjust") Float adjust,
                                                         @RequestParam(value = "sharpen") Float sharpen,
                                                         @RequestParam(value = "clarity") Float clarity,
                                                         @RequestParam(value = "saturation") Float saturation,
                                                         @RequestParam(value = "tone") Float tone,
                                                         @RequestParam(value = "whiteBalance") Float whiteBalance,
                                                         @RequestParam(value = "vignette") Float vignette,
                                                         @RequestParam(value = "grain") Float grain,
                                                         @RequestParam(value = "fade") Float fade,
                                                         @RequestParam(value = "splitTone") Float splitTone,
                                                         @RequestParam(value = "colorFilter") Float colorFilter) throws IOException {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        FilterCheckName filterCheckName = new FilterCheckName();

        // 필터 이름이 중복되는지 검사
        if (!filterService.nameCheck(name)) {
            filterCheckName.setName(name);
        } else {
            throw new AlreadyExistsException("Duplicate name");
        }

        if (image == null) {
            throw new BadRequestException("이미지를 넣어주세요.");
        }

        // upload img to storage
        String imageUrl = storageService.upload(image, "image");

        FilterModel filterModel = new FilterModel();

        filterModel.setImgUrl(imageUrl);
        filterModel.setName(name);
        filterModel.setUseCount(0);
        filterModel.setExposure(exposure);
        filterModel.setContrast(contrast);
        filterModel.setAdjust(adjust);
        filterModel.setSharpen(sharpen);
        filterModel.setClarity(clarity);
        filterModel.setSaturation(saturation);
        filterModel.setTone(tone);
        filterModel.setWhiteBalance(whiteBalance);
        filterModel.setVignette(vignette);
        filterModel.setGrain(grain);
        filterModel.setFade(fade);
        filterModel.setSplitTone(splitTone);
        filterModel.setColorFilter(colorFilter);

        filterService.save(email, imageUrl, filterModel);

        System.out.println(email);
        System.out.println("s3 url : " + imageUrl);
        System.out.println(filterModel);

        response.setStatusCode(HttpStatus.CREATED.value());

        return response;
    }

    @GetMapping
    public ApiResponseModel<FilterModel> apiGetFilter(@RequestBody String name, BindingResult bindingResult) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterService.getFilter(name);

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
