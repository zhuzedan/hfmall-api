package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.zzd.constant.PageConstant;
import org.zzd.domain.PostMessageReply;
import org.zzd.mapper.PostMessageReplyMapper;
import org.zzd.result.ResponseResult;
import org.zzd.service.PostMessageReplyService;
import org.zzd.utils.PageHelper;
import org.zzd.domain.PostMessage;
import org.zzd.service.PostMessageService;
import org.zzd.mapper.PostMessageMapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 主贴(PostMessage)表服务实现类
 *
 * @author zzd
 * @since 2023-02-26 17:30:23
 */
@Service("postMessageService")
public class PostMessageServiceImpl extends ServiceImpl<PostMessageMapper, PostMessage> implements PostMessageService {

    @Autowired
    private PostMessageReplyService postMessageReplyService;
    @Autowired
    private PostMessageMapper postMessageMapper ;
    @Autowired
    private PostMessageReplyMapper postMessageReplyMapper;

    @Override
    public ResponseResult<PageHelper<PostMessage>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<PostMessage> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<PostMessage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 起始日期
        if(!StringUtils.isBlank((CharSequence) params.get("startCreateTime"))){
            lambdaQueryWrapper.ge(PostMessage::getCreateTime,params.get("startCreateTime"));
        }
        // 结束日期
        if(!StringUtils.isBlank((CharSequence) params.get("endCreateTime"))){
            lambdaQueryWrapper.le(PostMessage::getCreateTime,params.get("endCreateTime"));
        }

        IPage<PostMessage> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }

    @Override
    public ResponseResult queryCircles() {
        List<PostMessage> postMessages = postMessageMapper.selectList(null);
        for (PostMessage postMessage : postMessages) {
            List<PostMessageReply> postMessageReplyList = postMessageReplyService.list(new QueryWrapper<PostMessageReply>().eq("post_id",postMessage.getId()));
            postMessage.setPostMessageReplyList(postMessageReplyList);
        }
        return ResponseResult.success(postMessages);
    }
}

