package qfx.service;

import qfx.model.FactorType;
import qfx.model.Privallige;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/13.
 */
public interface PrivalligeService {
    //增加
    void  addPrivallige(Privallige privallige);

    //删除
    void deletePrivallige(int id);

    //修改
    void updatePrivallige(Privallige privallige);

    //查询
    List<Privallige> selectPrivallige();
}
