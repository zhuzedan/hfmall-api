package org.zzd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.zzd.domain.SubCategory;
import org.zzd.mapper.SubCategoryMapper;
import org.zzd.service.SubCategoryService;
import org.springframework.stereotype.Service;

/**
 * 二级分类(SubCategory)表服务实现类
 *
 * @author zzd
 * @since 2023-01-07 22:34:01
 */
@Service("subCategoryService")
public class SubCategoryServiceImpl extends ServiceImpl<SubCategoryMapper, SubCategory> implements SubCategoryService {

}

