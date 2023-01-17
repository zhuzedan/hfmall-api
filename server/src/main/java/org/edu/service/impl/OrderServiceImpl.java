package org.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.edu.constant.PageConstant;
import org.edu.result.ResponseResult;
import org.edu.utils.PageCountUtil;
import org.edu.vo.RespPageBean;
import org.edu.domain.Order;
import org.edu.service.OrderService;
import org.edu.mapper.OrderMapper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * (Order)表服务实现类
 *
 * @author zzd
 * @since 2023-01-12 17:11:00
 */
@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Override
    public ResponseResult<RespPageBean<Order>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<Order> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        IPage<Order> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(RespPageBean.restPage(iPage));
    }
}

