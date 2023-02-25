package org.zzd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zzd.domain.Product;
import org.zzd.dto.LoginParam;
import org.zzd.mapper.NewsMapper;
import org.zzd.mapper.ProductMapper;
import org.zzd.result.ResponseResult;
import org.zzd.service.ActivityService;
import org.zzd.service.NewsService;
import org.zzd.service.ProductService;
import org.zzd.service.SwiperService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;

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

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ProductService productService;

    @ApiOperation("轮播图查")
    @PostMapping("/swiper")
    public ResponseResult querySwiper() {
        return swiperService.querySwiper();
    }

    @ApiOperation("资讯列表")
    @GetMapping("/news")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "文章题目", paramType = "query", dataType = "String"),
    })
    public ResponseResult queryNews(String title) {
        return newsService.queryNews(title);
    }

    @ApiOperation("资讯详情")
    @GetMapping("/readOneNew")
    public ResponseResult readNewDetails(Integer newId) {
        return newsService.readOneNew(newId);
    }

    @ApiOperation("查全部商品")
    @GetMapping("/queryAllProduct")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品名", paramType = "query", dataType = "String"),
    })
    public ResponseResult queryAllProduct(String name) {
        return productService.queryAllProduct(name);
    }

    @ApiOperation("查全部活动")
    @GetMapping("/queryActivities")
    public ResponseResult queryActivities() {
        return activityService.queryActivities();
    }

    @ApiOperation("参与活动")
    @PostMapping("/joinActivity")
    public ResponseResult joinActivity(@RequestParam String id) {
        return activityService.joinActivity(id);
    }
}
