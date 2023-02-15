package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.edu.constant.PageConstant;
import org.edu.domain.Product;
import org.edu.mapper.ProductMapper;
import org.edu.result.ResponseResult;
import org.edu.service.ProductService;
import org.edu.utils.PageCountUtil;
import org.edu.vo.PageHelper;
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


}

