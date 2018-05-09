package qfx.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qfx.model.FactorType;
import qfx.model.Log;
import qfx.model.User;
import qfx.service.FactorTypeService;
import qfx.service.LogService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by asus b250 on 2017/9/12.
 */
@Controller
@ResponseBody
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},maxAge = 3600,allowCredentials = "true")
public class FactorTypeController {
    @Autowired
    FactorTypeService factorTypeService;
    @Autowired
    LogService logService;
    @RequestMapping(value ="/addFactorType",method = {RequestMethod.POST,RequestMethod.GET},consumes = "application/json")
    public Map<String,Object> addFactorType(@RequestBody FactorType factorType,HttpServletRequest request){
        factorTypeService.addFactorType(factorType);
        ServletContext application = request.getServletContext();
        User user=(User)application.getAttribute("user");
        String  userName=user.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(userName);
        log.setText("factorType ADD");
        log.setUpdateTime(dateString);
        logService.addLog(log);
        Map<String,Object> param=new HashMap<>();
        param.put("增加成功",1);
        return param;
    }
    @RequestMapping(value ="/deleteFactorType",method = {RequestMethod.POST,RequestMethod.GET},consumes = "application/json")
    public Map<String,Object> deleteFactorType(@RequestBody FactorType factorType,HttpServletRequest request){
        int id=factorType.getId();
        factorTypeService.deleteFactorType(id);
        ServletContext application = request.getServletContext();
        User user=(User)application.getAttribute("user");
        String  userName=user.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(userName);
        log.setText("factorType DELETE");
        log.setUpdateTime(dateString);
        logService.addLog(log);
        Map<String,Object> param=new HashMap<>();
        param.put("删除成功",1);
        return param;
    }

    @RequestMapping(value ="/updateFactorType",method = {RequestMethod.POST,RequestMethod.GET},consumes = "application/json")
    public Map<String,Object> updateFactorType(@RequestBody FactorType factorType,HttpServletRequest request){
        factorTypeService.updateFactorType(factorType);
        ServletContext application = request.getServletContext();
        User user=(User)application.getAttribute("user");
        String  userName=user.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(userName);
        log.setText("factorType update");
        log.setUpdateTime(dateString);
        logService.addLog(log);
        Map<String,Object> param=new HashMap<>();
        param.put("修改成功",1);
        return param;
    }

    @RequestMapping(value ="/selectFactorType", method = RequestMethod.GET)
    public Map<String,Object>selectFactorType(){
        List<FactorType>list=factorTypeService.selectFactorType();
        Map<String,Object> param=new HashMap<>();
        param.put("查询成功",1);
        param.put("list",list);
        return param;
    }
     @RequestMapping(value ="/selectName",method = RequestMethod.GET)
     public Map<String,Object>selectName(){
        List<FactorType>list=factorTypeService.selectName();
        Map<String,Object> param=new HashMap<>();
        param.put("查询成功",1);
        param.put("list",list);
        return param;
    }

    @RequestMapping(value ="/selectOne",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object>selectOne(@RequestBody FactorType factorType){
        int fa=factorType.getTypeId();
        List<FactorType>list=factorTypeService.selectOne(fa);
        Map<String,Object> param=new HashMap<>();
        param.put("查询成功",1);
        param.put("list",list);
        return param;
    }
}
