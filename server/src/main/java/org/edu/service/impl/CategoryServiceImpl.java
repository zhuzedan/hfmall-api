package org.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.edu.domain.Category;
import org.edu.mapper.CategoryMapper;
import org.edu.result.ResponseResult;
import org.edu.service.CategoryService;
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

}

