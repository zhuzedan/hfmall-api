package org.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.edu.domain.Product;
import org.edu.domain.Swiper;
import org.edu.result.ResponseResult;
import org.edu.vo.RespPageBean;

import java.util.HashMap;


/**
 * 轮播图(Swiper)表服务接口
 *
 * @author zzd
 * @since 2023-01-03 22:40:33
 */
public interface SwiperService extends IService<Swiper> {

    ResponseResult<RespPageBean<Swiper>> queryPage(HashMap params);
}

