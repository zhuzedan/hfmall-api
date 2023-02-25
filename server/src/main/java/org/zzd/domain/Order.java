package org.zzd.domain;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * (Order)表实体类
 *
 * @author zzd
 * @since 2023-01-12 17:11:00
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_order")
public class Order implements Serializable {
    @TableId
    private Integer id;

    //订单号
    private String orderId;
    //用户id
    private String userId;
    //总价
    private Double totalPrice;
    //收货地址
    private String receiverAddress;
    //收货人
    private String receiverName;
    //联系电话
    private String receiverPhone;
    //订单创建日期
    private Date createTime;
    //更新时间
    private Date updateTime;
    //订单支付日期
    private Date payTime;
    //订单状态 0 未支付 1 已经支付
    private Integer status;
    //删除状态0删除1未删除
    @TableLogic(value = "0",delval = "1")
    private Integer isDeleted;
    //订单备注
    private String remark;

}

