package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.PostMessage;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;

/**
 * 主贴(PostMessage)表服务接口
 *
 * @author zzd
 * @since 2023-02-26 17:30:23
 */
public interface PostMessageService extends IService<PostMessage> {
    // 分页查询
    ResponseResult<PageHelper<PostMessage>> queryPage(HashMap params);

    ResponseResult queryCircles();

}

