package org.edu.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author :zzd
 * @date : 2022/12/22
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageHelper<T> {
    // 总记录数
    private Long totalCount;
    // 总页数
    private Long pageCount;
    // 分页数据
    private List<T> list;
}
