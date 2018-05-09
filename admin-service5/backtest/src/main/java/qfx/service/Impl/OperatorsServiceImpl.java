package qfx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qfx.mapper.OperatorsMapper;
import qfx.model.Operators;
import qfx.service.OperatorsService;

import java.util.List;
import java.util.Map;

/**
 * Created by asus b250 on 2017/9/13.
 */
@Service("OperatorsService")
public class OperatorsServiceImpl implements OperatorsService {
    @Autowired
    OperatorsMapper operatorMapper;
    //增加
    public void  addOperators(Operators operators){
        operatorMapper.addOperators(operators);
    }

    //删除
    public  void deleteOperators(int id){
        operatorMapper.deleteOperators(id);
    }

    //修改
    public void updateOperators(Operators operators){
        operatorMapper.updateOperators(operators);

    }

    //查询
    public List<Operators> selectOperators(){
        return operatorMapper.selectOperators();
    }

    public Integer operatorsId(String cnName){
        return operatorMapper.operatorsId(cnName);
    }
}
