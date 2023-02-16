package org.zzd.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.zzd.domain.Product;
import org.zzd.domain.ProductSwiperImage;
import org.zzd.dto.CreateProductDto;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.ProductService;
import org.zzd.service.ProductSwiperImageService;
import org.zzd.utils.PageHelper;
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
    public ResponseResult<PageHelper<Product>> findHot(@ApiIgnore @RequestParam HashMap params) {
        return productService.findHot(params);
    }

    /**
     * 根据条件分页查询商品
     * @param
     * @return
     */
    @ApiOperation(value = "分页查询商品")
    @PostMapping("queryProduct")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "integer",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", paramType = "query", dataType = "integer",defaultValue = "10"),
            @ApiImplicitParam(name = "name", value = "商品名", paramType = "query", dataType = "String")
    })
    public ResponseResult<PageHelper<Product>> queryProductPage(@ApiIgnore @RequestParam HashMap params) {
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
    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody CreateProductDto createProductDto) {
        return productService.createProduct(createProductDto);
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Product product) {
        productService.updateById(product);
        return new ResponseResult(200,"成功修改");
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("/delete")
    public ResponseResult delete(Long id) {
        boolean flag = productService.removeById(id);
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
        productService.removeByIds(idList);
        return new  ResponseResult(200,"批量删除成功");
    }
}

