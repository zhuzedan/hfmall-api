package org.zzd.controller;



import io.swagger.annotations.*;
import org.zzd.domain.Swiper;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.SwiperService;
import org.zzd.utils.PageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;

/**
 * 轮播图(Swiper)表控制层
 *
 * @author zzd
 * @since 2023-01-11 23:16:51
 */
@Api(tags = "轮播图")
@RestController
@RequestMapping("/api/swiper")
public class SwiperController {

    @Autowired
    private SwiperService swiperService;

    @ApiOperation(value = "轮播图列表")
    @PostMapping("/querySwiper")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
    })
    public ResponseResult<PageHelper<Swiper>> queryPage(@ApiIgnore @RequestParam HashMap params) {
        return swiperService.queryPage(params);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        Swiper swiper = swiperService.getById(id);
        return ResponseResult.success(swiper);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody Swiper swiper) {
        boolean flag = swiperService.save(swiper);
        if (flag) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Swiper swiper) {
        swiperService.updateById(swiper);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("delete")
    public ResponseResult delete(Long id) {
        boolean flag = swiperService.removeById(id);
        if (flag) {
            return ResponseResult.success();
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_COMPLETE.getMessage());
        }
    }
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemove")
    public ResponseResult batchRemove(@RequestBody List<Long> idList) {
        swiperService.removeByIds(idList);
        return new  ResponseResult(200,"批量删除成功");
    }
}

