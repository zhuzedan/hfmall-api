package org.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.edu.domain.Product;
import org.edu.result.ResponseResult;


/**
 * 商品表(Product)表服务接口
 *
 * @author zzd
 * @since 2023-01-04 13:58:31
 */
public interface ProductService extends IService<Product> {

    ResponseResult findHot();
}

