package org.zzd.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zzd.domain.Address;
import org.zzd.dto.CreateAddressDto;
import org.zzd.exception.ResponseException;
import org.zzd.result.ResponseResult;
import org.zzd.result.ResultCodeEnum;
import org.zzd.service.AddressService;

import java.util.Objects;

/**
 * @author :zzd
 * @date : 2023-02-24 23:10
 */
@RestController
@Api(tags = "APP我的接口")
@RequestMapping("/api/app-my")
public class AppMyController {
    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "新增地址")
    @PostMapping("/insertAddress")
    public ResponseResult insert(@RequestBody CreateAddressDto createAddressDto) {
        return addressService.createAddress(createAddressDto);
    }

    @ApiOperation(value = "列表展示地址")
    @GetMapping("/getAddress")
    public ResponseResult queryAddress() {
        return addressService.getAddress();
    }

    @ApiOperation(value = "获取地址详情")
    @GetMapping("/readAddress")
    public ResponseResult selectOne(Integer id) {
        Address address = addressService.getById(id);
        if (!Objects.isNull(address)) {
            return ResponseResult.success(address);
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }

    @ApiOperation(value = "修改地址数据")
    @PostMapping("/updateAddress")
    public ResponseResult update(@RequestBody Address address) {
        addressService.updateById(address);
        return ResponseResult.success();
    }

    @ApiOperation(value = "删除地址数据")
    @DeleteMapping("deleteAddress")
    public ResponseResult delete(Long id) {
        boolean flag = addressService.removeById(id);
        if (flag) {
            return ResponseResult.success();
        }
        else {
            throw new ResponseException(ResultCodeEnum.PARAM_NOT_VALID.getCode(), ResultCodeEnum.PARAM_NOT_VALID.getMessage());
        }
    }
}
