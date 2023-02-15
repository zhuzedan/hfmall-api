package org.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.edu.domain.Product;
import org.edu.result.ResponseResult;
import org.edu.vo.PageHelper;

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
}

