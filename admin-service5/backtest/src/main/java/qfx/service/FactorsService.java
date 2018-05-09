package qfx.service;

import qfx.model.Factors;

import java.util.List;
import java.util.Map;

/**
 * Created by asus b250 on 2017/9/14.
 */
public interface FactorsService {
    //增加
    void  addFactors(Factors factors);

    //删除
    void deleteFactors(int id);
    //修改
    void  updateFactors(Factors factors);
    //查找
    List<Factors> selectFactors(Map<String,Object>param);
    List<Factors>selectAudit(int auditState,String userCname);

    int selectId(String cnName);

    //修改审核状态
    void updateState(Factors factors);

    //删除alphaH 中的数据
    void  deleteAlname(String name);

    //查看详情
    List<Factors>showDetail(int id);
    //判断因子英文名不能重复，查询cnName
    List<Factors>selectCname();
    //根据typeId查询因子类型
    List<Factors>selectType(int typeId);
}
