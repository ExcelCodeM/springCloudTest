package com.test.service;

import com.test.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author Breeze
 * @since 2020-06-29
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> getUserPermission(Long userid);
}
