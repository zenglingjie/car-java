package qfx.service;

import qfx.model.User;
import java.util.List;
import java.util.Map;

/**
 * Created by asus b250 on 2017/9/11.
 */
public interface UserService {
    //用户增加
    void  addUser(User user);
    //用户删除
    void  deleteUser(int id);
    //用户修改
    void updateUser(User user);
    //用户查找
    List<User> selectUser();

    //查询所有具有审核权限的人
    List<User> auditUser();

    User selectpw(String cnName);
}
