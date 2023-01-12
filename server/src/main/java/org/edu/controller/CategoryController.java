package org.edu.controller;

import org.edu.domain.Category;
import org.edu.result.ResponseResult;
import org.edu.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * (Category)表控制层
 *
 * @author zzd
 * @since 2023-01-04 19:01:26
 */
@Api(tags = "商品分类")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查询所有大分类名称")
    @GetMapping("/queryAll")
    public ResponseResult findAll() {
        List<Category> list = categoryService.list();
        return ResponseResult.success(list);
    }
    @ApiOperation(value = "查询大分类下的小分类")
    @GetMapping("/querySubCategories")
    public ResponseResult querySubCategories() {
        return categoryService.querySubCategories();
    }

    @ApiOperation(value = "查询大分类下的小分类+商品")
    @GetMapping("/queryCategories")
    public ResponseResult queryProductList() {
        return categoryService.queryProductList();
    }

    @ApiOperation(value = "获取详情")
    @GetMapping("/read")
    public ResponseResult selectOne(Long id) {
        Category category = categoryService.getById(id);
        return ResponseResult.success(category);
    }

    @ApiOperation(value = "新增数据")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody Category category) {
        boolean flag = categoryService.save(category);
        if (flag) {
            return new ResponseResult(200,"成功新增");
        }else {
            return new ResponseResult(401,"新增失败");
        }
    }

    @ApiOperation(value = "修改数据")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Category category) {
        categoryService.updateById(category);
        return new ResponseResult(200,"成功修改");
    }

    @ApiOperation(value = "删除数据")
    @DeleteMapping("delete/{id}")
    public ResponseResult delete(@PathVariable Long id) {
        boolean flag = categoryService.removeById(id);
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
        categoryService.removeByIds(idList);
        return new  ResponseResult(200,"批量删除成功");
    }
}

