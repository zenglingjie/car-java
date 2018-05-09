package qfx.mapper;

import qfx.model.FactorType;
import qfx.model.Settings;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/21.
 */
public interface SettingsMapper {
    //增加
    void  addSettings(Settings settings);

    //删除
    void deleteSettings(int id);

    //修改
    void updateSettings(Settings settings);

    //查询
    List<Settings> selectSettings(int type);
}
