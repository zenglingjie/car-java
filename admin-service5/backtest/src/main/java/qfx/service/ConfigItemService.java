package qfx.service;

import qfx.model.ConfigItem;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/21.
 */
public interface ConfigItemService {
    //增加
    void  addConfigItem(ConfigItem configItem);

    //删除
    void deleteConfigItem(int id);

    //修改
    void updateConfigItem(ConfigItem configItem);

    //查询
    List<ConfigItem> selectConfigItem();

    Integer  selectId(String cnName);
}
