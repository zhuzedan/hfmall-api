package org.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.edu.domain.Product;
import org.edu.domain.ProductSwiperImage;
import org.edu.result.ResponseResult;
import org.edu.service.ProductService;
import org.edu.service.ProductSwiperImageService;
import org.edu.vo.RespPageBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;

/**
 * 商品表(Product)表控制层
 *
 * @author zzd
 * @since 2023-01-04 14:02:08
 */
@Api(tags = "商品")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductSwiperImageService productSwiperImageService;

    /**
     * 查询热门
     * @return
     */
    @ApiOperation(value = "查询热门商品")
    @GetMapping("/hotProduct")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
    })
    public ResponseResult<RespPageBean<Product>> findHot(@ApiIgnore @RequestParam HashMap params) {
        return productService.findHot(params);
    }

    /**
     * 根据条件分页查询商品
     * @param
     * @return
     */
    @ApiOperation(value = "分页查询商品")
    @GetMapping("queryProduct")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
    })
    public ResponseResult<RespPageBean<Product>> queryProductPage(@ApiIgnore @RequestParam HashMap params) {
        return ResponseResult.success(productService.queryProductPage(params));
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Integer id) {
        Product product = productService.getById(id);
        List<ProductSwiperImage> productSwiperImageList = productSwiperImageService.list(new QueryWrapper<ProductSwiperImage>().eq("productId", product.getId()).orderByAsc("sort"));
        product.setProductSwiperImageList(productSwiperImageList);
        return ResponseResult.success(product);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody Product product) {
        boolean flag = productService.save(product);
        if (flag) {
            return new ResponseResult(200,"成功新增");
        }else {
            return new ResponseResult(401,"新增失败");
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Product product) {
        productService.updateById(product);
        return new ResponseResult(200,"成功修改");
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        boolean flag = productService.removeById(id);
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
        productService.removeByIds(idList);
        return new  ResponseResult(200,"批量删除成功");
    }
}

