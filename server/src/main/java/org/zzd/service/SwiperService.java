package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.Swiper;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;


/**
 * 轮播图(Swiper)表服务接口
 *
 * @author zzd
 * @since 2023-01-03 22:40:33
 */
public interface SwiperService extends IService<Swiper> {

    ResponseResult<PageHelper<Swiper>> queryPage(HashMap params);
}

