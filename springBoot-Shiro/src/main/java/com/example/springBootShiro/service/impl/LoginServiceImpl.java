package com.example.springBootShiro.service.impl;

import com.example.springBootShiro.bean.Permission;
import com.example.springBootShiro.bean.Role;
import com.example.springBootShiro.bean.User;
import com.example.springBootShiro.service.LoginService;
import com.example.springBootShiro.shiro.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {


    @Autowired
    private PasswordHelper passwordHelper;

    @Override
    public User getUserByName(String getMapByName) {
        //模拟数据库查询，正常情况此处是从数据库或者缓存查询。
        return getMapByName(getMapByName);
    }


    /**
     * 模拟数据库查询
     * @param userName
     * @return
     */
    private User getMapByName(String userName){
        //共添加两个用户，两个用户都是admin一个角色，
        //wsl有query和add权限，zhangsan只有一个query权限
        Permission permissions1 = new Permission("1","query");
        Permission permissions2 = new Permission("2","add");
        Set<Permission> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        Role role = new Role("1","admin",permissionsSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User("1","aaj","123456",roleSet);
        passwordHelper.encryptPassword(user);
        Map<String ,User> map = new HashMap<>();
        map.put(user.getUserName(), user);

        Permission permissions3 = new Permission("3","query");
        Set<Permission> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions3);
        Role role1 = new Role("2","user",permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User("2","bbj","123456",roleSet1);
        passwordHelper.encryptPassword(user1);
        map.put(user1.getUserName(), user1);
        return map.get(userName);
    }
}
