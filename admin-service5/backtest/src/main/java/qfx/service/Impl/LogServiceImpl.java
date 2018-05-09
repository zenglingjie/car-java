package qfx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qfx.mapper.LogMapper;
import qfx.model.Log;
import qfx.service.LogService;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/13.
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogMapper logMapper;
    //增加
    public void  addLog(Log log){
        logMapper.addLog(log);
    }

    //删除
    public void deleteLog(int id){
        logMapper.deleteLog(id);
    }


    //查询
    public List<Log> selectLog(){
        return  logMapper.selectLog();
    }
}
