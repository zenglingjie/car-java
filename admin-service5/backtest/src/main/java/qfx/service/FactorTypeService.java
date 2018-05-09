package qfx.service;

import qfx.model.FactorType;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/13.
 */

    import qfx.model.FactorType;
import qfx.model.Factors;

import java.util.List;

    /**
     * Created by asus b250 on 2017/9/12.
     */
    public interface FactorTypeService{

        //增加
        void  addFactorType(FactorType factorType);


        //删除
        void deleteFactorType(int id);

        //修改
        void updateFactorType(FactorType factorType);

        //查询
        List<FactorType> selectFactorType();

        Integer selectId(String enName);

        //查询下拉框所有因子名称
        List<FactorType>selectName();
        //根据因子类型,查询因子中文名
        List<FactorType>selectOne(int typeId);
        //根据因子类型查询typeId
       List<FactorType>selectFatype(String  factorType);


    }


