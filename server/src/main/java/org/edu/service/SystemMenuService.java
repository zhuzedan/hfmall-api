package org.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.edu.domain.SystemMenu;
import org.edu.result.ResponseResult;
import org.edu.vo.RouterVo;

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

