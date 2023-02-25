package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.Address;
import org.zzd.dto.CreateAddressDto;
import org.zzd.result.ResponseResult;
import org.zzd.utils.PageHelper;

import java.util.HashMap;

/**
 * (Address)表服务接口
 *
 * @author zzd
 * @since 2023-02-24 23:11:37
 */
public interface AddressService extends IService<Address> {
    // 分页查询
    ResponseResult<PageHelper<Address>> queryPage(HashMap params);

    //app添加收货地址
    ResponseResult createAddress(CreateAddressDto createAddressDto);

    ResponseResult getAddress();

}

