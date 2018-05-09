package qfx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qfx.mapper.FactorTypeMapper;
import qfx.model.FactorType;
import qfx.model.Factors;
import qfx.service.FactorTypeService;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/12.
 */
@Service("FactorTypeService")
public class FactorTypeServiceImpl implements FactorTypeService {
    @Autowired
    FactorTypeMapper factorTypeMapper;
    //增加
    public void  addFactorType(FactorType factorType){
        factorTypeMapper.addFactorType(factorType);
    }

    //删除
    public void deleteFactorType(int id){
      factorTypeMapper.deleteFactorType(id);
    }

    //修改
    public void updateFactorType(FactorType factorType){
       factorTypeMapper.updateFactorType(factorType);
    }

    //查询
    public List<FactorType> selectFactorType(){
       return factorTypeMapper.selectFactorType();

    }
    public Integer  selectId(String name){
        return  factorTypeMapper.selectId(name);
    }
    //查询下拉框所有因子名称
    public List<FactorType>selectName(){
      return  factorTypeMapper.selectName();
    }

    //根据因子类型,查询因子中文名
    public List<FactorType>selectOne(int typeId){
        return factorTypeMapper.selectOne(typeId);
    }
    //根据因子类型查询typeId
    public List<FactorType>selectFatype(String  factorType){
        return factorTypeMapper.selectFatype(factorType);
    }

}
