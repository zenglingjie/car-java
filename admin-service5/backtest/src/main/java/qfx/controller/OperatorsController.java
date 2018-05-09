package qfx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import qfx.model.FactorType;
import qfx.model.Operators;
import qfx.service.FactorTypeService;
import qfx.service.OperatorsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus b250 on 2017/9/13.
 */
@Controller
@ResponseBody
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},maxAge = 3600,allowCredentials = "true")
public class OperatorsController {

    @Autowired
    OperatorsService operatorsService;
    @RequestMapping(value ="/addOperators",method = RequestMethod.GET)
    public Map<String,Object> addOperators(String enName, String cnName,int FactorId,int isDefault){
        Operators operators=new Operators();
        operators.setCnName(cnName);
        operators.setEnName(enName);
        operators.setFactorId(FactorId);
        operators.setIsDefault(isDefault);
        operatorsService.addOperators(operators);
        Map<String,Object> param=new HashMap<>();
        param.put("增加成功",1);
        return param;
    }

    @RequestMapping(value ="/deleteOperators",method = RequestMethod.GET)
    public Map<String,Object> deleteOperators(int factorId){
        operatorsService.deleteOperators(factorId);
        Map<String,Object> param=new HashMap<>();
        param.put("删除成功",1);
        return param;
    }

    @RequestMapping(value ="/updateOperators",method = RequestMethod.GET)
    public Map<String,Object> updateOperators(String enName, String cnName,int factorId,int isDefault){
        Operators operators=new Operators();
        operators.setFactorId(factorId);
        operators.setEnName(enName);
        operators.setCnName(cnName);
        operators.setIsDefault(isDefault);
        operatorsService.updateOperators(operators);
        Map<String,Object> param=new HashMap<>();
        param.put("修改成功",1);
        return param;
    }

    @RequestMapping(value ="/selectOperators",method = RequestMethod.GET)
    public Map<String,Object>selectOperators(){
        List<Operators> list=operatorsService.selectOperators();
        Map<String,Object> param=new HashMap<>();
        param.put("查询成功",1);
        param.put("list",list);
        return param;
    }
}
