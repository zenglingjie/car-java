package qfx.controller;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qfx.Util.fileLoad;
import qfx.model.*;
import qfx.service.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by asus b250 on 2017/9/14.
 */
@Controller
@ResponseBody
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},maxAge = 3600,allowCredentials = "true")
public class FactorsController {
    @Autowired
    FactorsService factorsService;
    @Autowired
    OperatorsService operatorsService;
    @Autowired
    FactorTypeService factorTypeService;
    @Autowired
    LogService  logService;
	 //@RequiresPermissions("factor:c")
    @RequestMapping(value ="/addFactors",method = {RequestMethod.POST,RequestMethod.GET},consumes = "application/json")
    public void addFactors(@RequestBody Factors factors,HttpServletRequest request,HttpServletResponse response){
      /*  fileLoad fileLoad=new fileLoad();
        fileLoad.addFactorss(factors,request,response);*/
         int isHot=factors.getIsHot();
         if(isHot==1){
             factors.setIsHotName("是");
         }else{
             factors.setIsHotName("否");
         }
       String admin=factors.getAdminUser();
        String factorType=factors.getFactorType();
        List<Operators>operators=factors.getOperators();
        StringBuffer sB = new StringBuffer();
        for(int j=0;j<operators.size();j++){
            sB.append(operators.get(j).getName()+",");
        }
        if (',' == sB.charAt(sB.length() - 1))
            sB = sB.deleteCharAt(sB.length() - 1);
        String str=sB.toString();
        String[] strArray = null;
        strArray = str.split(",");
        int typeId= factorTypeService.selectId(factorType);
        factors.setTypeId(typeId);
        /*1 启用   2默认*/
        factors.setIndexEnable(2);
        factors.setLoadEnable(2);
        factors.setIsValid(2);
        factors.setAuditState(2);
        factors.setOperaNames(str);
        factors.setAuditStaNaTrue(1);
        factors.setAuditStaNaFalse(0);
        factors.setIsPassAudit(0);
        factorsService.addFactors(factors);
        //增加日志
        ServletContext application = request.getServletContext();
        User user=(User)application.getAttribute("user");
        String  userName=user.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(userName);
        log.setText("FACTOR ADD");
        log.setUpdateTime(dateString);
        logService.addLog(log);
        String  factorValue=null;
        for(int z=0;z<strArray.length;z++){
            factorValue= strArray[z];
            int factorId=factors.getId();
            Operators operatorss=new Operators();
            operatorss.setFactorId(factorId);
            operatorss.setCnName(factorValue);
            operatorss.setIsDefault(0);
            operatorsService.addOperators(operatorss);
        }
    }
    //@RequiresPermissions("factor:d")
    @RequestMapping(value ="/deleteFactors",method = {RequestMethod.POST,RequestMethod.GET}, consumes = "application/json")
    public Map<String,Object> deleteFactorType(@RequestBody Factors factors,HttpServletRequest request){
        int id=factors.getId();
        factorsService.deleteFactors(id);
        ServletContext application = request.getServletContext();
        User user=(User)application.getAttribute("user");
        String  userName=user.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(userName);
        log.setText("FACTOR DELETE");
        log.setUpdateTime(dateString);
        logService.addLog(log);
        Map<String,Object> param=new HashMap<>();
        param.put("删除成功",1);
        return param;
    }
    @RequestMapping(value ="/updateFactors",method ={RequestMethod.POST,RequestMethod.GET},consumes = "application/json")
    public Map<String,Object> updateFactorType(@RequestBody Factors factors,HttpServletRequest request){
        String factorType=factors.getFactorType();
        List<FactorType>aa=factorTypeService.selectFatype(factorType);
        int typeId=aa.get(0).getTypeId();
        factors.setTypeId(typeId);
        String isHotName=factors.getIsHotName();
        if(isHotName.equals("1")){
            factors.setIsHotName("是");
        }else{
            factors.setIsHotName("否");
        }
        int sut=factors.getAuditState();
        String cnName=factors.getCnName();
        List<Operators>operators=factors.getOperators();
        int factorId=factors.getId();
        operatorsService.deleteOperators(factorId);
        StringBuffer sB = new StringBuffer();
        for(int j=0;j<operators.size();j++){
            sB.append(operators.get(j).getName()+",");
        }
        if (',' == sB.charAt(sB.length() - 1))
            sB = sB.deleteCharAt(sB.length() - 1);
        String str=sB.toString();
        String[] strArray = null;
        strArray = str.split(",");
        String  factorValue=null;
        for(int z=0;z<strArray.length;z++){
            factorValue= strArray[z];
            Operators operatorss=new Operators();
            operatorss.setFactorId(factorId);
            operatorss.setCnName(factorValue);
            operatorss.setIsDefault(0);
            operatorsService.addOperators(operatorss);
        }
        String filePath=factors.getFilePath();
        factors.setOperaNames(str);
        factorsService.updateFactors(factors);
        ServletContext application = request.getServletContext();
        User  user=(User)application.getAttribute("user");
        String  userName=user.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(userName);
        log.setText("FACTOR UPDATE");
        log.setUpdateTime(dateString);
        logService.addLog(log);
        Map<String,Object> param=new HashMap<>();
        param.put("修改成功",1);
        return param;
    }
   @RequestMapping(value ="/selectFactors",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object>selectFactorType(@RequestBody SelectOne selectOne){
       int typeId=0;
        String factorType=selectOne.getFactorType();
        if(factorType!=null&factorType!=""){
            typeId=factorTypeService.selectId(factorType);
       }
        String cnName=selectOne.getCnName();
        int   indexEnable=selectOne.getIndexEnable();
        int valid=selectOne.getIsValid();
        int loadEnable=selectOne.getLoadEnable();
        String description=selectOne.getDescription();
        String enName=selectOne.getEnName();
        Map<String,Object> param=new HashMap<>();
        param.put("indexEnable",indexEnable);
        param.put("loadEnable",loadEnable);
        param.put("isValid",valid);
        param.put("cnName",cnName);
        param.put("description",description);
        param.put("enName",enName);
        param.put("typeId",typeId);
        List<Factors> list=factorsService.selectFactors(param);
        Map<String,Object> params=new HashMap<>();
        params.put("查询成功",1);
        params.put("list",list);
        return params;
    }


    @RequestMapping(value ="/selectCname",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object>selectCname(){
        List<Factors> list=factorsService.selectCname();
        Map<String,Object> params=new HashMap<>();
        params.put("查询成功",1);
        params.put("list",list);
        return params;
    }

    //查看详情
    @RequestMapping(value ="/showDetail",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object>showDetail(@RequestBody  Factors factors){
        int id=factors.getId();
        List<Factors> list=factorsService.showDetail(id);
        Map<String,Object> params=new HashMap<>();
        params.put("查询成功",1);
        params.put("list",list);
        return params;
    }
     /*审核*/
    @RequestMapping(value ="/AuditState",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object>selectvalid(@RequestBody  Factors factors){
        String userCname=factors.getUserCname();
        factors.setAuditState(3);
        int auditState=factors.getAuditState();
        List<Factors>Afactors=factorsService.selectAudit(auditState,userCname);
        Map<String,Object> params=new HashMap<>();
        params.put("查询成功",1);
        params.put("Afactors",Afactors);
        return params;
    }

    @RequestMapping(value ="/selectType",method = {RequestMethod.POST,RequestMethod.GET})
    public Map<String,Object>selectType(@RequestBody FactorType factorType){
        int typeId=factorType.getTypeId();
        List<Factors>FactorType=factorsService.selectType(typeId);
        Map<String,Object> params=new HashMap<>();
        params.put("查询成功",1);
        params.put("list",FactorType);
        return params;
    }
}