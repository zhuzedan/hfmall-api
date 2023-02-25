package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.zzd.constant.PageConstant;
import org.zzd.domain.Swiper;
import org.zzd.mapper.SwiperMapper;
import org.zzd.result.ResponseResult;
import org.zzd.service.SwiperService;
import org.zzd.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 轮播图(Swiper)表服务实现类
 *
 * @author zzd
 * @since 2023-01-03 22:40:33
 */
@Service("swiperService")
public class SwiperServiceImpl extends ServiceImpl<SwiperMapper, Swiper> implements SwiperService {
    @Autowired
    private SwiperMapper swiperMapper;

    @Override
    public ResponseResult<PageHelper<Swiper>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<Swiper> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<Swiper> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        IPage<Swiper> iPage = swiperMapper.selectPage(page, lambdaQueryWrapper);
        List<Swiper> list = iPage.getRecords();

        return ResponseResult.success(PageHelper.restPage(iPage));
    }

    @Override
    public ResponseResult querySwiper() {
        List<Swiper> swipers = swiperMapper.selectList(new QueryWrapper<Swiper>().eq("status",1));
        return ResponseResult.success(swipers);
    }
}

