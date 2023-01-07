package org.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.edu.domain.Category;
import org.edu.result.ResponseResult;


/**
 * (Category)表服务接口
 *
 * @author zzd
 * @since 2023-01-04 19:01:26
 */
public interface CategoryService extends IService<Category> {

    //查询分类下的小分类
    ResponseResult querySubCategories();
    //查询分类加商品
    ResponseResult queryProductList();
}

