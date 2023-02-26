package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.PostMessageReply;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;

/**
 * 回帖表(PostMessageReply)表服务接口
 *
 * @author zzd
 * @since 2023-02-26 17:36:14
 */
public interface PostMessageReplyService extends IService<PostMessageReply> {
    // 分页查询
    ResponseResult<PageHelper<PostMessageReply>> queryPage(HashMap params);
}

