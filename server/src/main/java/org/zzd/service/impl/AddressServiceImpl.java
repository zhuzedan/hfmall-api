package org.zzd.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.zzd.constant.PageConstant;
import org.zzd.domain.News;
import org.zzd.dto.CreateAddressDto;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;
import org.zzd.domain.Address;
import org.zzd.service.AddressService;
import org.zzd.mapper.AddressMapper;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * (Address)表服务实现类
 *
 * @author zzd
 * @since 2023-02-24 23:11:37
 */
@Service("addressService")
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public ResponseResult<PageHelper<Address>> queryPage(HashMap params) {
        int pageNum = Integer.parseInt((String) params.get(PageConstant.PAGE_NUM));
        int pageSize = Integer.parseInt((String) params.get(PageConstant.PAGE_SIZE));
        Page<Address> page = new Page(pageNum, pageSize);

        LambdaQueryWrapper<Address> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 起始日期
        if(!StringUtils.isBlank((CharSequence) params.get("startCreateTime"))){
            lambdaQueryWrapper.ge(Address::getCreateTime,params.get("startCreateTime"));
        }
        // 结束日期
        if(!StringUtils.isBlank((CharSequence) params.get("endCreateTime"))){
            lambdaQueryWrapper.le(Address::getCreateTime,params.get("endCreateTime"));
        }

        IPage<Address> iPage = this.page(page, lambdaQueryWrapper);
        return ResponseResult.success(PageHelper.restPage(iPage));
    }

    @Override
    public ResponseResult createAddress(CreateAddressDto createAddressDto) {
        Address address = new Address(createAddressDto);
        address.setIsDeleted("0");
        address.setCreateTime(new Date());
        addressMapper.insert(address);
        return ResponseResult.success(address.getId());
    }

    @Override
    public ResponseResult getAddress() {
        LambdaQueryWrapper<Address> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Address> addresses = addressMapper.selectList(lambdaQueryWrapper);
        return ResponseResult.success(addresses);
    }
}

