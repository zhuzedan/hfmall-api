package org.zzd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zzd.dto.LoginParam;
import org.zzd.mapper.NewsMapper;
import org.zzd.result.ResponseResult;
import org.zzd.service.NewsService;
import org.zzd.service.SwiperService;

/**
 * @author :zzd
 * @date : 2023-02-22 19:24
 */
@RestController
@Api(tags = "APP首页接口")
@RequestMapping("/api/app-index")
public class AppIndexController {
    @Autowired
    private SwiperService swiperService;

    @Autowired
    private NewsService newsService;

    @ApiOperation("轮播图查")
    @PostMapping("/swiper")
    public ResponseResult querySwiper() {
        return swiperService.querySwiper();
    }

    @ApiOperation("资讯列表")
    @PostMapping("/news")
    public ResponseResult queryNews() {
        return newsService.queryNews();
    }
}
