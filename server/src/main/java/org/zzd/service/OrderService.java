package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.Order;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;

/**
 * (Order)表服务接口
 *
 * @author zzd
 * @since 2023-01-12 17:11:00
 */
public interface OrderService extends IService<Order> {
    // 分页查询
    ResponseResult<PageHelper<Order>> queryPage(HashMap params);
}

