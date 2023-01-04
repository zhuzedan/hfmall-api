package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.edu.domain.Product;
import org.edu.mapper.ProductMapper;
import org.edu.result.ResponseResult;
import org.edu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 商品表(Product)表服务实现类
 *
 * @author zzd
 * @since 2023-01-04 13:58:31
 */
@Service("productService")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ResponseResult findHot() {
        Page<Product> page = new Page<>(1,8);
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<Product>().eq("isHot", 1).orderByAsc("hotDateTime");
        Page<Product> pageProduct = productMapper.selectPage(page, productQueryWrapper);
        List<Product> hotProductList = pageProduct.getRecords();
        return ResponseResult.success(hotProductList);
    }
}

