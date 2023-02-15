package org.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.edu.domain.Order;
import org.edu.result.ResponseResult;
import org.edu.vo.PageHelper;

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

