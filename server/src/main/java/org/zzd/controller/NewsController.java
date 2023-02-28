package org.zzd.controller;

import org.zzd.domain.News;
import org.zzd.service.NewsService;
import org.zzd.utils.PageHelper;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResultCodeEnum;
import org.zzd.result.ResponseResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * (News)表控制层
 *
 * @author zzd
 * @since 2023-02-25 20:34:31
 */
@Api(tags = "汉服资讯")
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "分页查询")
    @PostMapping("/queryPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
            @ApiImplicitParam(name = "title", value = "新闻标题", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "startCreateTime", value = "起始日期", paramType = "query", dataType = "date"),
            @ApiImplicitParam(name = "endCreateTime", value = "结束日期", paramType = "query", dataType = "date")
    })
    public ResponseResult<PageHelper<News>> queryPage(@ApiIgnore @RequestParam HashMap params) {
        return newsService.queryPage(params);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        News news = newsService.getById(id);
        if (!Objects.isNull(news)) {
            return ResponseResult.success(news);
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody News news) {
        boolean flag = newsService.save(news);
        if (flag) {
            return ResponseResult.success();
        }else {
            throw new ResponseException(ResultCodeEnum.DATA_ERROR.getCode(), ResultCodeEnum.DATA_ERROR.getMessage());
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody News news) {
        newsService.updateById(news);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("delete")
    public ResponseResult delete(Long id) {
        boolean flag = newsService.removeById(id);
        if (flag) {
            return ResponseResult.success();
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }
    @ApiOperation(value = "批量删除数据")
    @DeleteMapping("/batchRemove")
    public ResponseResult batchRemove(@RequestBody List<Long> idList) {
        newsService.removeByIds(idList);
        return ResponseResult.success();
    }
}

