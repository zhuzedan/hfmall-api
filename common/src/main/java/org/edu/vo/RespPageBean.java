package org.edu.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :zzd
 * @date : 2023-01-12 1:48
 */
@Data
@NoArgsConstructor
public class RespPageBean<T> {
    //总条数
    private Long  totalCount;
    //总页数
    private Long totalPage;
    //当前页数
    private Long pageNum;
    //页面大小
    private Long pageSize;
    //返回数据
    private List<T> data;

    /**
     * 分页
     */
    public RespPageBean(IPage<T> page) {
        this.data = page.getRecords();
        this.totalCount = page.getTotal();
    }

    public RespPageBean(Long totalCount, List<T> data) {
        this.totalCount = totalCount;
        this.data = data;
    }

    public RespPageBean(Long totalCount, Long totalPage,Long pageNum,Long pageSize, List<T> data) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.data = data;
    }
}
