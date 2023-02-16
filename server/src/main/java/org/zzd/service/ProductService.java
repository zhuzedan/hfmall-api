package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.Product;
import org.zzd.dto.CreateProductDto;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;


/**
 * 商品表(Product)表服务接口
 *
 * @author zzd
 * @since 2023-01-04 13:58:31
 */
public interface ProductService extends IService<Product> {


    PageHelper<Product> queryProductPage(HashMap params);


    ResponseResult<PageHelper<Product>> findHot(HashMap params);

    //创建商品
    ResponseResult createProduct(CreateProductDto createProductDto);
}

