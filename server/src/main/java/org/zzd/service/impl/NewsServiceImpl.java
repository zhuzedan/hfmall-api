package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.zzd.constant.PageConstant;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;
import org.zzd.domain.News;
import org.zzd.service.NewsService;
import org.zzd.mapper.NewsMapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * (News)表服务实现类
 *
 * @author zzd
 * @since 2023-02-22 21:16:25
 */
@Service("newsService")
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public ResponseResult queryNews() {
        List<News> news = newsMapper.selectList(new QueryWrapper<News>());
        return ResponseResult.success(news);
    }
}

