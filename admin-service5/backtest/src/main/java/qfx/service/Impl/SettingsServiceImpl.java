package qfx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qfx.mapper.SettingsMapper;
import qfx.model.Settings;
import qfx.service.SettingsService;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/21.
 */
@Service("SettingsService")
public class SettingsServiceImpl implements SettingsService {
    @Autowired
    SettingsMapper settingsMapper;
    //增加
    public void  addSettings(Settings settings){
        settingsMapper.addSettings(settings);
    }

    //删除
    public void deleteSettings(int id){
        settingsMapper.deleteSettings(id);
    }

    //修改
    public void updateSettings(Settings settings){
        settingsMapper.updateSettings(settings);
    }

    //查询
    public List<Settings> selectSettings(int type){
       return     settingsMapper.selectSettings(type);
    }
}
