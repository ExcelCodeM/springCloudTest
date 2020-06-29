package com.test.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.entity.Permission;
import com.test.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：Breeze
 * @date ：Created in 2020/6/25 21:35
 * @description：
 */
@Service
public class StringUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            return null;
        }else {

            List<GrantedAuthority> grantedAuthoritys = new ArrayList<>();
            List<Permission> userPermissionList = permissionService.getUserPermission(user.getId());

            userPermissionList.forEach(permission -> {
                if(permission != null && permission.getEnname() != null){
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getEnname());
                    grantedAuthoritys.add(grantedAuthority);
                }
            });

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthoritys);
        }


    }
}
