package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.zzd.domain.Category;
import org.zzd.domain.Product;
import org.zzd.domain.SubCategory;
import org.zzd.mapper.CategoryMapper;
import org.zzd.result.ResponseResult;
import org.zzd.service.CategoryService;
import org.zzd.service.ProductService;
import org.zzd.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Category)表服务实现类
 *
 * @author zzd
 * @since 2023-01-04 19:01:26
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    //查分类
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private ProductService productService;
    @Override
    public ResponseResult querySubCategories() {
        List<Category> categoryList = categoryMapper.selectList(null);
        for (Category category: categoryList) {
            List<SubCategory> subCategoryList = subCategoryService.list(new QueryWrapper<SubCategory>().eq("categoryId", category.getId()));
            category.setSubCategoryList(subCategoryList);
        }
        return ResponseResult.success(categoryList);
    }
    //查分类加商品
    @Override
    public ResponseResult queryProductList() {
        List<Category> categoryList = categoryMapper.selectList(null);
        for (Category category: categoryList) {
            List<SubCategory> subCategoryList = subCategoryService.list(new QueryWrapper<SubCategory>().eq("categoryId", category.getId()));
            category.setSubCategoryList(subCategoryList);
            for (SubCategory subCategory: subCategoryList) {
                List<Product> productList = productService.list(new QueryWrapper<Product>().eq("sub_category_id",subCategory.getId()));
                subCategory.setProductList(productList);
            }
        }
        return ResponseResult.success(categoryList);
    }
}

