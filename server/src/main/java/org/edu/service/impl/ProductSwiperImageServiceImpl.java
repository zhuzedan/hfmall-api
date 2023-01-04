package org.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.edu.domain.ProductSwiperImage;
import org.edu.mapper.ProductSwiperImageMapper;
import org.edu.service.ProductSwiperImageService;
import org.springframework.stereotype.Service;

/**
 * 产品轮播图图片(ProductSwiperImage)表服务实现类
 *
 * @author zzd
 * @since 2023-01-04 19:51:06
 */
@Service("productSwiperImageService")
public class ProductSwiperImageServiceImpl extends ServiceImpl<ProductSwiperImageMapper, ProductSwiperImage> implements ProductSwiperImageService {

}

