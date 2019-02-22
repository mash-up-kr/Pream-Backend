package com.mashup6th.preambackend.controller;

import com.mashup6th.preambackend.dto.filter.FilterCheckName;
import com.mashup6th.preambackend.dto.filter.FilterModel;
import com.mashup6th.preambackend.exception.AlreadyExistsException;
import com.mashup6th.preambackend.exception.BadRequestException;
import com.mashup6th.preambackend.model.ApiResponseModel;
import com.mashup6th.preambackend.service.FilterService;
import com.mashup6th.preambackend.service.StorageService;
import com.mashup6th.preambackend.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    private UserService userService;

    public FilterController(
            FilterService filterService,
            StorageService storageService,
            UserService userService) {
        this.filterService = filterService;
        this.storageService = storageService;
        this.userService = userService;
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
                                                         @RequestParam(value = "description") String description,
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

        if (userService.checkLogin(email)) {
            System.out.println("로그인 상태 확인 완료");
        } else {
            throw new BadRequestException("로그인 상태가 아닙니다.");
        }

        if (image == null) {
            throw new BadRequestException("이미지를 넣어주세요.");
        }

        // upload img to storage
        String imageUrl = storageService.upload(image, "image");

        FilterModel filterModel = new FilterModel();

        filterModel.setImgUrl(imageUrl);
        filterModel.setName(name);
        filterModel.setDescription(description);
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

    @GetMapping("/{filterId}")
    public ApiResponseModel<FilterModel> apiGetFilter(@PathVariable Long filterId) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        FilterModel filterModel = filterService.getFilter(filterId);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.toString());
        response.setResult(filterModel);

        return response;
    }

    @GetMapping("/list/{email}")
    public ApiResponseModel<List<FilterModel>> apiGetFilters(@PathVariable String email) {
        ApiResponseModel<List<FilterModel>> response = new ApiResponseModel<>();

        List<FilterModel> filterModels = filterService.getFilterList(email);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.toString());
        response.setResult(filterModels);

        return response;
    }



    @DeleteMapping("/delete/{filterId}")
    public ApiResponseModel<FilterModel> apiDeleteFilter(@PathVariable Long filterId, @RequestParam String email) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterService.delete(filterId, email);
        response.setStatusCode(HttpStatus.OK.value());

        return response;
    }

}
