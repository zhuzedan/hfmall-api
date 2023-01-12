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
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class RespPageBean<T> {
    //总条数
    private Long  totalCount;
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
}
