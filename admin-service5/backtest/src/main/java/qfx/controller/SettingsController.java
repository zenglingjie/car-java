package qfx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import qfx.model.FactorType;
import qfx.model.Settings;
import qfx.service.ConfigItemService;
import qfx.service.FactorTypeService;
import qfx.service.SettingsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus b250 on 2017/9/21.
 */
@Controller
@ResponseBody
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},maxAge = 3600,allowCredentials = "true")
public class SettingsController{
    @Autowired
    SettingsService  settingsService;
    @Autowired
    ConfigItemService configItemService;

    @RequestMapping(value ="/addSettings",method = RequestMethod.GET)
    public Map<String,Object> addFactorType(String cnName){
        int  configId=configItemService.selectId(cnName);
        Settings  settings=new Settings();
        settings.setName(cnName);
        settings.setType(configId);
        settings.setEnable(0);
        settingsService.addSettings(settings);
        Map<String,Object> param=new HashMap<>();
        param.put("增加成功",1);
        return param;
    }

    @RequestMapping(value ="/deleteSettings",method = RequestMethod.GET)
    public Map<String,Object> deleteFactorType(int id){
        settingsService.deleteSettings(id);
        Map<String,Object> param=new HashMap<>();
        param.put("删除成功",1);
        return param;
    }

    @RequestMapping(value ="/updateSettings",method = RequestMethod.GET)
    public Map<String,Object> updateFactorType(int id, String name){
        Settings settings=new Settings();
        settings.setId(id);
        settings.setName(name);
        settingsService.updateSettings(settings);
        Map<String,Object> param=new HashMap<>();
        param.put("修改成功",1);
        return param;
    }

    @RequestMapping(value ="/selectSettings",method = RequestMethod.GET)
    public Map<String,Object>selectFactorType(int type){
        List<Settings> list=settingsService.selectSettings(type);
        Map<String,Object> param=new HashMap<>();
        param.put("查询成功",1);
        param.put("list",list);
        return param;
    }
}
