package qfx.mapper;

import qfx.model.ConfigItem;
import qfx.model.FactorType;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/21.
 */
public interface ConfigItemMapper {
    //增加
    void  addConfigItem(ConfigItem configItem);

    //删除
    void deleteConfigItem(int id);

    //修改
    void updateConfigItem(ConfigItem configItem);

    //查询
    List<ConfigItem> selectConfigItem();

    //根据中文名查找configItem中的id
    Integer  selectId(String cnName);
}
