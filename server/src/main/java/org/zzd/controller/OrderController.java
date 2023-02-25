package org.zzd.controller;

import org.zzd.domain.Order;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.OrderService;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;

/**
 * (Order)表控制层
 *
 * @author zzd
 * @since 2023-01-12 17:11:00
 */
@Api(tags = "商品订单")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "分页查询")
    @PostMapping("/queryPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
    })
    public ResponseResult<PageHelper<Order>> queryPage(@ApiIgnore @RequestParam HashMap params) {
        return orderService.queryPage(params);
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        Order order = orderService.getById(id);
        return ResponseResult.success(order);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody Order order) {
        boolean flag = orderService.save(order);
        if (flag) {
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Order order) {
        orderService.updateById(order);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("delete")
    public ResponseResult delete(Long id) {
        boolean flag = orderService.removeById(id);
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
        orderService.removeByIds(idList);
        return new  ResponseResult(200,"批量删除成功");
    }
}

