package com.test.mapper;

import com.test.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author Breeze
 * @since 2020-06-29
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户userid，获取用户权限列表
     * @param userid
     * @return
     */
    List<Permission> getUserPermission(@Param("userid") Long userid);

}
