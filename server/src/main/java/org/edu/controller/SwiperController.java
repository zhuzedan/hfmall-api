package org.edu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.edu.domain.Swiper;
import org.edu.result.ResponseResult;
import org.edu.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author :zzd
 * @date : 2023/1/3
 */
@Api(tags = "轮播图")
@RestController
@RequestMapping("/api")
public class SwiperController {
    @Autowired
    private SwiperService swiperService;
    @ApiOperation(value = "轮播图列表")
    @GetMapping("/querySwiper")
    public ResponseResult findAll() {
        List<Swiper> list = swiperService.list();
        return ResponseResult.success(list);
    }
}
