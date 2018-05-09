package qfx.mapper;

import qfx.model.FactorType;
import qfx.model.Log;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/13.
 */
public interface LogMapper {
    //增加
    void  addLog(Log log);

    //删除
    void deleteLog(int id);


    //查询
    List<Log> selectLog();
}
