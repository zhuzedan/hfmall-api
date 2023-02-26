package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.zzd.constant.PageConstant;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;
import org.zzd.domain.PostMessageReply;
import org.zzd.service.PostMessageReplyService;
import org.zzd.mapper.PostMessageReplyMapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * 回帖表(PostMessageReply)表服务实现类
 *
 * @author zzd
 * @since 2023-02-26 17:36:14
 */
@Service("postMessageReplyService")
public class PostMessageReplyServiceImpl extends ServiceImpl<PostMessageReplyMapper, PostMessageReply> implements PostMessageReplyService {

    @Override
    public ResponseResult<PageHelper<PostMessageReply>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<PostMessageReply> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<PostMessageReply> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 起始日期
        if(!StringUtils.isBlank((CharSequence) params.get("startCreateTime"))){
            lambdaQueryWrapper.ge(PostMessageReply::getCreateTime,params.get("startCreateTime"));
        }
        // 结束日期
        if(!StringUtils.isBlank((CharSequence) params.get("endCreateTime"))){
            lambdaQueryWrapper.le(PostMessageReply::getCreateTime,params.get("endCreateTime"));
        }

        IPage<PostMessageReply> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }
}

