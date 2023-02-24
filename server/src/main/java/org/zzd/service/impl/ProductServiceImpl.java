package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.zzd.constant.PageConstant;
import org.zzd.domain.News;
import org.zzd.domain.Product;
import org.zzd.domain.SystemUser;
import org.zzd.dto.CreateProductDto;
import org.zzd.mapper.ProductMapper;
import org.zzd.result.ResponseResult;
import org.zzd.service.ProductService;
import org.zzd.utils.PageCountUtil;
import org.zzd.utils.PageHelper;
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

    //分页查询商品
    @Override
    public PageHelper<Product> queryProductPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<Product> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<Product> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //用户名
        if (!StringUtils.isBlank((CharSequence) params.get("name"))) {
            lambdaQueryWrapper.like(Product::getName,params.get("name"));
        }
        IPage<Product> iPage = this.page(page, lambdaQueryWrapper);
        List<Product> list = iPage.getRecords();
        Long pageCount = PageCountUtil.countPage( iPage.getTotal(), pageSize);
        return new PageHelper<>(iPage.getTotal(),pageCount, iPage.getCurrent(),iPage.getSize(),list);
    }

    @Override
    public ResponseResult<PageHelper<Product>> findHot(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<Product> page = new Page(pageNum, pageSize);

        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<Product>().eq("isHot", 1).orderByAsc("hotDateTime");

        IPage<Product> iPage = this.page(page, productQueryWrapper);
        List<Product> list = iPage.getRecords();
        return ResponseResult.success(new PageHelper<>(iPage.getTotal(),list));
    }

    //创建商品
    @Override
    public ResponseResult createProduct(CreateProductDto createProductDto) {
        Product product = new Product(createProductDto);
        productMapper.insert(product);
        return ResponseResult.success();
    }

    //查全部商品
    @Override
    public ResponseResult queryAllProduct() {
        QueryWrapper<Product> productQueryWrapper = new QueryWrapper<Product>();
        List<Product> products = productMapper.selectList(productQueryWrapper);
        return ResponseResult.success(products);

    }


}

