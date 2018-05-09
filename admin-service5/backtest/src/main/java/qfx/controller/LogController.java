package qfx.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qfx.model.FactorType;
import qfx.model.Factors;
import qfx.model.Log;
import qfx.service.FactorTypeService;
import qfx.service.LogService;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by asus b250 on 2017/9/13.
 */
@Controller
@ResponseBody
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},maxAge = 3600,allowCredentials = "true")
public class LogController {
    @Autowired
    LogService logService;
    @RequestMapping(value ="/addLog",method = RequestMethod.GET)
    public Map<String,Object> addFactorType(String text,String name){
        Log log=new Log();
        log.setName(name);
        log.setText(text);
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        log.setUpdateTime(dateString);
        logService.addLog(log);
        Map<String,Object> param=new HashMap<>();
        param.put("增加成功",1);
        return param;
    }
    @RequestMapping(value ="/deleteLog",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object> deleteFactorType(@RequestBody  Log log){
        int id=log.getId();
        logService.deleteLog(id);
        Map<String,Object> param=new HashMap<>();
        param.put("删除成功",1);
        return param;
    }

    @RequestMapping(value ="/selectLog",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object>selectFactorType(){
        List<Log> list=logService.selectLog();
        Map<String,Object> param=new HashMap<>();
        param.put("查询成功",1);
        param.put("list",list);
        return param;
    }
}
