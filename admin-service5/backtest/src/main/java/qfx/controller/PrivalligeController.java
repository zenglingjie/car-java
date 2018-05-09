package qfx.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import qfx.model.Operators;
import qfx.model.Privallige;
import qfx.service.OperatorsService;
import qfx.service.PrivalligeService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by asus b250 on 2017/9/13.
 */
@Controller
@ResponseBody
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},maxAge = 3600,allowCredentials = "true")
public class PrivalligeController {
    @Autowired
    PrivalligeService privalligeService;
    @RequestMapping(value ="/addPrivallige",method = RequestMethod.GET)
    public Map<String,Object> addOperators(Integer value){
        Privallige privallige=new Privallige();
        privallige.setValue(value);
        privalligeService.addPrivallige(privallige);
        Map<String,Object> param=new HashMap<>();
        param.put("增加成功",1);
        return param;
    }

    @RequestMapping(value ="/deletePrivallige",method = RequestMethod.GET)
    public Map<String,Object> deleteOperators(int id){
        privalligeService.deletePrivallige(id);
        Map<String,Object> param=new HashMap<>();
        param.put("删除成功",1);
        return param;
    }

    @RequestMapping(value ="/updatePrivallige",method = RequestMethod.GET)
    public Map<String,Object> updateOperators(int id,int value){
        Privallige privallige=new Privallige();
        privallige.setId(id);
        privallige.setValue(value);
        privalligeService.updatePrivallige(privallige);
        Map<String,Object> param=new HashMap<>();
        param.put("修改成功",1);
        return param;
    }

    @RequestMapping(value ="/selectPrivallige",method = RequestMethod.GET)
    public Map<String,Object>selectOperators(){
        List<Privallige> list=privalligeService.selectPrivallige();
        Map<String,Object> param=new HashMap<>();
        param.put("查询成功",1);
        param.put("list",list);
        return param;
    }

}
