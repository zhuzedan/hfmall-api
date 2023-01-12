package org.edu.controller;



import io.swagger.annotations.*;
import org.edu.domain.Product;
import org.edu.domain.Swiper;
import org.edu.result.ResponseResult;
import org.edu.service.SwiperService;
import org.edu.vo.RespPageBean;
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
@RequestMapping("/api")
public class SwiperController {

    @Autowired
    private SwiperService swiperService;

    @ApiOperation(value = "轮播图列表")
    @GetMapping("/querySwiper")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
    })
    public ResponseResult<RespPageBean<Swiper>> queryPage(@ApiIgnore @RequestParam HashMap params) {
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
    @DeleteMapping("delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        boolean flag = swiperService.removeById(id);
        if (flag) {
            return new ResponseResult(200,"删除成功");
        }
        else {
            return new ResponseResult(200,"失败");
        }
    }
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemove")
    public ResponseResult batchRemove(@RequestBody List<Long> idList) {
        swiperService.removeByIds(idList);
        return new  ResponseResult(200,"批量删除成功");
    }
}

