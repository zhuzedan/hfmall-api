package org.zzd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :zzd
 * @date : 2023-02-24 23:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressDto {
    //收货人姓名
    private String name;
    //性别;1=男,2=女,3=未知
    private String gender;
    //电话号码
    private String phone;
    //收货地址省市区
    private String address;
    //详细地址
    private String detailAddress;
    //是否设为默认（1默认  0不默认）
    private String ifDefault;
}
