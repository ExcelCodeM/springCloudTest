package com.test.service.impl;

import com.test.entity.Permission;
import com.test.mapper.PermissionMapper;
import com.test.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Breeze
 * @since 2020-06-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getUserPermission(Long userid) {
        return permissionMapper.getUserPermission(userid);
    }
}
