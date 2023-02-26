package org.zzd.domain;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 回帖表(PostMessageReply)表实体类
 *
 * @author zzd
 * @since 2023-02-26 17:36:14
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_post_message_reply")
public class PostMessageReply implements Serializable {
    //主键id@TableId
    private Integer id;

    //父id
    private Integer postId;
    //回帖内容
    private String replyContent;
    //回帖者
    private String replyUser;
    //回复时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //说明
    private String remark;

}

