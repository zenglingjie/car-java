package qfx.service;

import qfx.model.Log;
import qfx.model.User;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/13.
 */
public interface LogService {

    //增加
     void  addLog(Log log);

    //删除
    void deleteLog(int id);


    //查询
    List<Log> selectLog();
}
