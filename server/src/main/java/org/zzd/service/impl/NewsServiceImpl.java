package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.zzd.constant.PageConstant;
import org.zzd.domain.Activity;
import org.zzd.domain.SystemRole;
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
    public ResponseResult queryNews(String title) {
        LambdaQueryWrapper<News> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //文章名
        if (!StringUtils.isBlank(title)) {
            lambdaQueryWrapper.like(News::getTitle,title);
        }
        List<News> news = newsMapper.selectList(lambdaQueryWrapper);
        return ResponseResult.success(news);
    }

    @Override
    public ResponseResult readOneNew(Integer newId) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",newId);
        News oneNew = newsMapper.selectOne(queryWrapper);
        Integer clickCount = oneNew.getClick();
        clickCount += 1;
        oneNew.setClick(clickCount);
        newsMapper.updateById(oneNew);
        return ResponseResult.success(oneNew);
    }

    @Override
    public ResponseResult<PageHelper<News>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<News> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<News> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据名称查询
        if(!StringUtils.isBlank((CharSequence) params.get("title"))){
            lambdaQueryWrapper.like(News::getTitle,params.get("title"));
        }
        // 起始日期
        if(!StringUtils.isBlank((CharSequence) params.get("startCreateTime"))){
            lambdaQueryWrapper.ge(News::getCreateTime,params.get("startCreateTime"));
        }
        // 结束日期
        if(!StringUtils.isBlank((CharSequence) params.get("endCreateTime"))){
            lambdaQueryWrapper.le(News::getCreateTime,params.get("endCreateTime"));
        }

        IPage<News> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }
}

