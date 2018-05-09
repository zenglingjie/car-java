package qfx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qfx.mapper.UserMapper;
import qfx.model.User;
import qfx.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * Created by asus b250 on 2017/9/11.
 */
@Service("userService")
public class userServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    //用户增加
    public void  addUser(User user){
        userMapper.addUser(user);
    }
    //用户删除
    public void  deleteUser(int id){
        userMapper.deleteUser(id);
    }
    //用户修改
    public void updateUser(User user){
        userMapper.updateUser(user);
    }
    //用户查找
    public List<User> selectUser(){
        return  userMapper.selectUser();
    }
    public User selectpw(String cnName){
        return userMapper.selectpw(cnName);
    }
    //查询所有具有审核权限的人
    public List<User> auditUser(){
        return userMapper.auditUser();
    }
}
