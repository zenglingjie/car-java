package qfx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qfx.mapper.ConfigItemMapper;
import qfx.model.ConfigItem;
import qfx.service.ConfigItemService;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/21.
 */
@Service("ConfigItemService")
public class ConfigItemServiceImpl implements ConfigItemService {
    @Autowired
    ConfigItemMapper configItemMapper;
    //增加
    public  void  addConfigItem(ConfigItem configItem){
        configItemMapper.addConfigItem(configItem);
    }

    //删除
    public void deleteConfigItem(int id){
        configItemMapper.deleteConfigItem(id);
    }

    //修改
    public void updateConfigItem(ConfigItem configItem){
        configItemMapper.updateConfigItem(configItem);
    }

    //查询
    public List<ConfigItem> selectConfigItem(){
        return  configItemMapper.selectConfigItem();
    }

    public Integer  selectId(String cnName){
       return configItemMapper.selectId(cnName);
    }
}
