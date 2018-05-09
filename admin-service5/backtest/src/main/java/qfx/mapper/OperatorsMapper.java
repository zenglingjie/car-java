package qfx.mapper;

import qfx.model.FactorType;
import qfx.model.Operators;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/13.
 */
public interface OperatorsMapper {
    //增加
    void  addOperators(Operators operators);

    //删除
    void deleteOperators(int factorId);

    //修改
    void updateOperators(Operators operators);

    //查询
    List<Operators> selectOperators();

    Integer operatorsId(String cnName);

}
