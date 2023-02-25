package org.zzd.domain;


import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.zzd.dto.CreateAddressDto;

/**
 * (Address)表实体类
 *
 * @author zzd
 * @since 2023-02-24 23:11:36
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_address")
public class Address implements Serializable {
    //主键id
    @TableId
    private Integer id;

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
    //状态
    private String status;
    //是否设为默认（1默认  0不默认）
    private String ifDefault;
    //创建时间
    private Date createTime;
    //是否删除（0不删 1删）
    @TableLogic(value = "0",delval = "1")
    private String isDeleted;

    public Address(CreateAddressDto createAddressDto) {
        this.name = createAddressDto.getName();
        this.gender = createAddressDto.getGender();
        this.phone = createAddressDto.getPhone();
        this.address = createAddressDto.getAddress();
        this.detailAddress = createAddressDto.getDetailAddress();
        this.ifDefault = createAddressDto.getIfDefault();
    }

}

