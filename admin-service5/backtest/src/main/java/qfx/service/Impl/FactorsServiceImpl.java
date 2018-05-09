package qfx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qfx.mapper.FactorsMapper;
import qfx.model.Factors;
import qfx.service.FactorsService;

import java.util.List;
import java.util.Map;

/**
 * Created by asus b250 on 2017/9/14.
 */
@Service("FactorsService")
public class FactorsServiceImpl implements FactorsService {
    @Autowired
    FactorsMapper factorsMapper;
    //增加
   public  void  addFactors(Factors factors){
       factorsMapper.addFactors(factors);

    }
    //删除
   public  void deleteFactors(int id){
       factorsMapper.deleteFactors(id);

    }
    //修改
    public void  updateFactors(Factors factors){
        factorsMapper.updateFactors(factors);

    }
    //查找
    public List<Factors> selectFactors(Map<String,Object>param){
        return  factorsMapper.selectFactors(param);
    }
 /*  public List<Factors> selectFactors(Factors factors){
        return  factorsMapper.selectFactors(factors);
}*/

    public int selectId(String cnName){
        return factorsMapper.selectId(cnName);
    }
    public List<Factors>selectAudit(int auditState,String userCname){
        return factorsMapper.selectAudit(auditState,userCname);
    }

    //修改审核状态
    public void updateState(Factors factors){
        factorsMapper.updateState(factors);
    }
    //删除alphaH 中的数据
    public void  deleteAlname(String enName){
        factorsMapper.deleteAlname(enName);
    }

    //查看详情
    public List<Factors>showDetail(int id){
        return  factorsMapper.showDetail(id);
    }
    //判断因子英文名不能重复，查询cnName
    public List<Factors>selectCname(){
        return  factorsMapper.selectCname();

    }
    //根据typeId查询因子类型
    public List<Factors>selectType(int typeId){
        return  factorsMapper.selectType(typeId);
    }


}
