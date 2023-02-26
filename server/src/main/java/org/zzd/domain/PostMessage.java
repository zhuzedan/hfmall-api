package org.zzd.domain;

import java.util.Date;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 主贴(PostMessage)表实体类
 *
 * @author zzd
 * @since 2023-02-26 17:30:23
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_post_message")
public class PostMessage implements Serializable {
    //主键
    @TableId
    private Integer id;

    //标题
    private String title;
    //内容
    private String content;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //是否删除（0没删1删了）
    @TableLogic(value = "0",delval = "1")
    private String isDeleted;
    //发帖人
    private String createBy;
    //手机号
    private String phone;
    //头像
    private String headUrl;
    //评论
    @TableField(select = false)
    private List<PostMessageReply> postMessageReplyList;

}

