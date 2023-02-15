package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.zzd.constant.PageConstant;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;
import org.zzd.domain.Order;
import org.zzd.service.OrderService;
import org.zzd.mapper.OrderMapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * (Order)表服务实现类
 *
 * @author zzd
 * @since 2023-01-12 17:11:00
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public ResponseResult<PageHelper<Order>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<Order> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        IPage<Order> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }
}

