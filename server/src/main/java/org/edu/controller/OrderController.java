package org.edu.controller;

import org.edu.domain.Order;
import org.edu.service.OrderService;
import org.edu.result.ResponseResult;
import org.edu.vo.PageHelper;
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
    @DeleteMapping("delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        boolean flag = orderService.removeById(id);
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
        orderService.removeByIds(idList);
        return new  ResponseResult(200,"批量删除成功");
    }
}

