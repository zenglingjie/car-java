package qfx.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import qfx.model.ConfigItem;
import qfx.model.Operators;
import qfx.service.ConfigItemService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asus b250 on 2017/9/21.
 */
@Controller
@ResponseBody
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},maxAge = 3600,allowCredentials = "true")
public class ConfigItemController {
    @Autowired
    ConfigItemService configItemService;
    @RequestMapping(value ="/addconfigItem",method = RequestMethod.GET)
    public Map<String,Object> addConversion(String enName, String cnName,int defaultValueId){
        ConfigItem configItem=new ConfigItem();
        configItem.setDefaultValueId(defaultValueId);
        configItem.setCnName(cnName);
        configItem.setEnName(enName);
        configItemService.addConfigItem(configItem);
        Map<String,Object> param=new HashMap<>();
        param.put("增加成功",1);
        return param;
    }

    @RequestMapping(value ="/deleteconfigItem",method = RequestMethod.GET)
    public Map<String,Object> deleteConversion(int id){
        configItemService.deleteConfigItem(id);
        Map<String,Object> param=new HashMap<>();
        param.put("删除成功",1);
        return param;
    }

    @RequestMapping(value ="/updateconfigItem",method = RequestMethod.GET)
    public Map<String,Object> updateConversion(int id,String cnName, String enName,int defaultValueId){
        ConfigItem configItem=new ConfigItem();
        configItem.setId(id);
        configItem.setDefaultValueId(defaultValueId);
        configItem.setEnName(enName);
        configItem.setCnName(cnName);
        configItemService.updateConfigItem(configItem);
        Map<String,Object> param=new HashMap<>();
        param.put("修改成功",1);
        return param;
    }

    @RequestMapping(value ="/selectconfigItem",method = RequestMethod.GET)
    public Map<String,Object>selectConversion(){
        List<ConfigItem> list=configItemService.selectConfigItem();
        Map<String,Object> param=new HashMap<>();
        param.put("查询成功",1);
        param.put("list",list);
        return param;
    }
}
