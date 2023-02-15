package org.zzd.utils;

/**
 * 分页工具类 计算出页数
 * @author :zzd
 * @date : 2023-01-13 16:26
 */
public class PageCountUtil {
    //计算总页数
    public static long countPage(long total, Integer pageSize) {
        Long subCount = total%pageSize;
        Long pageCount;
        if (subCount == 0) {
            pageCount = total / pageSize;
        }else  {
            pageCount = total/pageSize+1;
        }
        return pageCount;
    }

}
