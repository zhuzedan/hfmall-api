package org.zzd.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zzd.domain.SystemMenu;
import org.zzd.result.ResponseResult;
import org.zzd.vo.RouterVo;

import java.util.List;


/**
 * 菜单表(SystemMenu)表服务接口
 *
 * @author zzd
 * @since 2023-01-06 22:01:51
 */
public interface SystemMenuService extends IService<SystemMenu> {

    List<RouterVo> getUserMenuList(Long id);

    List<String> getUserButtonList(Long id);

    ResponseResult findNodes();

    ResponseResult removeMenuById(Long id);
}

