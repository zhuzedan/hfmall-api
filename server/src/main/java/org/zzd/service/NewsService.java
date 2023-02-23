package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.News;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;

/**
 * (News)表服务接口
 *
 * @author zzd
 * @since 2023-02-22 21:16:25
 */
public interface NewsService extends IService<News> {

    ResponseResult queryNews();
}

