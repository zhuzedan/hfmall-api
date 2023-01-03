package org.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.edu.domain.Swiper;
import org.edu.mapper.SwiperMapper;
import org.edu.service.SwiperService;
import org.springframework.stereotype.Service;

/**
 * 轮播图(Swiper)表服务实现类
 *
 * @author zzd
 * @since 2023-01-03 22:40:33
 */
@Service("swiperService")
public class SwiperServiceImpl extends ServiceImpl<SwiperMapper, Swiper> implements SwiperService {

}

