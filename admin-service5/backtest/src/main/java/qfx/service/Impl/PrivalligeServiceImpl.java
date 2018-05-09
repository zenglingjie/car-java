package qfx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import qfx.mapper.PrivalligeMapper;
import qfx.model.FactorType;
import qfx.model.Privallige;
import qfx.service.PrivalligeService;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/13.
 */
@Service("PrivalligeService")
@ResponseBody
public class PrivalligeServiceImpl  implements PrivalligeService{
    @Autowired
    PrivalligeMapper privalligeMapper;

    //增加
    public void  addPrivallige(Privallige privallige){
        privalligeMapper.addPrivallige(privallige);
    }

    //删除
    public void deletePrivallige(int id){
        privalligeMapper.deletePrivallige(id);
    }

    //修改
    public void updatePrivallige(Privallige privallige){
        privalligeMapper.updatePrivallige(privallige);
    }

    //查询
    public List<Privallige> selectPrivallige(){
        return  privalligeMapper.selectPrivallige();
    }

}
