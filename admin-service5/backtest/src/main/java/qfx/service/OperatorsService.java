package qfx.service;

import qfx.model.Operators;

import java.util.List;
import java.util.Map;

/**
 * Created by asus b250 on 2017/9/13.
 */
public interface OperatorsService {
    //增加
    void addOperators(Operators operators);

    //删除
    void deleteOperators(int id);

    //修改
    void updateOperators(Operators operators);
    //查询
    List<Operators> selectOperators();

    Integer operatorsId(String cnName);
}