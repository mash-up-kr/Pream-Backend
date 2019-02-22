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
            @ApiResponse(code = 201, message = "Filter Created", response = FilterModel.class),
            @ApiResponse(code = 400, message = "No content. image does not exist"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Check the user email. User who has this email does not exist"),
            @ApiResponse(code = 500, message = "Failure")})
    @ApiOperation(value = "apiCreateFilter", notes = "이미지를 넣지 않았을 경우 : no content / 현재 email을 가지는 user가 존재하지 않을 때 에러")
    @RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ApiResponseModel<FilterModel> apiCreateFilter(@RequestParam(value = "image") MultipartFile image,
                                                         @RequestParam(value = "email") String email,
                                                         @RequestParam(value = "name") String name,
                                                         @RequestParam(value = "description") String description,
                                                         @RequestParam(value = "exposure") Float exposure,
                                                         @RequestParam(value = "brightness") Float brightness,
                                                         @RequestParam(value = "contrast") Float contrast,
                                                         @RequestParam(value = "sharpen") Float sharpen,
                                                         @RequestParam(value = "saturation") Float saturation,
                                                         @RequestParam(value = "highlight") Float highlight,
                                                         @RequestParam(value = "shadow") Float shadow,
                                                         @RequestParam(value = "vignette") Float vignette,
                                                         @RequestParam(value = "grain") Float grain,
                                                         @RequestParam(value = "fade") Float fade,
                                                         @RequestParam(value = "colorFilterColor") Integer colorFilterColor,
                                                         @RequestParam(value = "colorFilterValue") Float colorFilterValue) throws IOException {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        if (userService.checkLogin(email)) {
            log.info("로그인 상태 확인 완료");
        } else {
            throw new BadRequestException("로그인 상태가 아닙니다.");
        }

        if (image.isEmpty()) {
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
        filterModel.setSharpen(sharpen);
        filterModel.setBrightness(brightness);
        filterModel.setHighlight(highlight);
        filterModel.setShadow(shadow);
        filterModel.setColorFilterColor(colorFilterColor);
        filterModel.setColorFilterValue(colorFilterValue);
        filterModel.setSaturation(saturation);
        filterModel.setVignette(vignette);
        filterModel.setGrain(grain);
        filterModel.setFade(fade);


        filterService.save(email, imageUrl, filterModel);

        System.out.println(email);
        System.out.println("s3 url : " + imageUrl);
        System.out.println(filterModel);

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage(HttpStatus.CREATED.toString());

        return response;
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "No content. This filterId does not exist"),
        @ApiResponse(code = 500, message = "Failure")})
    @ApiOperation(value = "apiGetFilter", notes = "현재 filterId에 해당하는 필터가 존재하지 않을 때 에러")
    @GetMapping("/{filterId}")
    public ApiResponseModel<FilterModel> apiGetFilter(@PathVariable Long filterId) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        FilterModel filterModel = filterService.getFilter(filterId);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.toString());
        response.setResult(filterModel);

        return response;
    }

    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Check the user email. User who has this email does not exist"),
        @ApiResponse(code = 500, message = "Failure")})
    @ApiOperation(value = "apiGetFilters", notes = "현재 사용자가 가지고 있는(다운받은) 필터 불러오는 api")
    @GetMapping("/list/{email}")
    public ApiResponseModel<List<FilterModel>> apiGetFilters(@PathVariable String email) {
        ApiResponseModel<List<FilterModel>> response = new ApiResponseModel<>();

        List<FilterModel> filterModels = filterService.getFilterList(email);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.toString());
        response.setResult(filterModels);

        return response;
    }


    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success. Deleted"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "User who has this email does not exist / User does not have this filter"),
        @ApiResponse(code = 500, message = "Failure")})
    @ApiOperation(value = "apiDeleteFilter", notes = "현재 사용자가 가지고 있는 필터를 삭제한 '후' 호출하는 api")
    @DeleteMapping("/delete/{filterId}")
    public ApiResponseModel<FilterModel> apiDeleteFilter(@PathVariable Long filterId, @RequestParam String email) {
        ApiResponseModel<FilterModel> response = new ApiResponseModel<>();

        filterService.delete(filterId, email);

        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage(HttpStatus.OK.toString());

        return response;
    }

}
