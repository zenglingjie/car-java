package qfx.controller;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authc.AuthenticationException;
import javax.servlet.ServletContext;
import javax.servlet.http.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import qfx.model.Log;
import qfx.model.User;
import qfx.service.LogService;
import qfx.service.UserService;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by asus b250 on 2017/9/11.
 */
@Controller
@ResponseBody
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},maxAge = 3600,allowCredentials = "true")
public class UserController{
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @RequestMapping(value = "/addUser", method = {RequestMethod.POST,RequestMethod.GET},consumes = "application/json")
    public Map<String, Object> addUser(@RequestBody User user,HttpServletRequest request) {
        String[] priName=user.getPriName();
       /* String pri=org.apache.commons.lang.StringUtils.join(priName);*/
        String str1= StringUtils.join(priName, ",");
        user.setPri(str1);
        String  md5Password=user.getMd5password();
        String  password=user.getPassword();
        userService.addUser(user);
        ServletContext application = request.getServletContext();
        User loginuser=(User)application.getAttribute("user");
        String cnName=loginuser.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(cnName);
        log.setText("USER ADD");
        log.setUpdateTime(dateString);
        logService.addLog(log);
        Map<String, Object> param = new HashMap<>();
        param.put("增加成功",1);
        return param;
    }

    @RequestMapping(value = "/deleteUser",method = {RequestMethod.POST,RequestMethod.GET}, consumes = "application/json")
    public Map<String, Object> deleteUser(@RequestBody User user,HttpServletRequest request) {
        userService.deleteUser(user.getId());
        ServletContext application = request.getServletContext();
        User loginuser=(User)application.getAttribute("user");
        String cnName=loginuser.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(cnName);
        log.setText("USER DELETE");
        log.setUpdateTime(dateString);
        logService.addLog(log);
        Map<String, Object> param = new HashMap<>();
        param.put("删除成功", 1);
        return param;
    }

    @RequestMapping(value = "/updateUser", method = {RequestMethod.POST,RequestMethod.GET}, consumes = "application/json")
    @ResponseBody
    public Map<String, Object> updateUser(@RequestBody User user,HttpServletRequest request) {
        int number=user.getPasNumber();
        user.setPasNumber(number);
        String[]priName=user.getPriName();
        String str1= StringUtils.join(priName, ",");
        user.setPri(str1);
        String md5Password=user.getMd5password();
        userService.updateUser(user);
        ServletContext application = request.getServletContext();
        User loginuser=(User)application.getAttribute("user");
        String cnName=loginuser.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(cnName);
        log.setText("USER UPDATE");
        log.setUpdateTime(dateString);
        logService.addLog(log);
        Map<String, Object> param = new HashMap<>();
        param.put("修改成功", 1);
        return param;
    }

    @RequestMapping(value ="/selectUser",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object>selectUser(HttpServletResponse response){
        List<User>users=userService.selectUser();
        Map<String,Object>params=new HashMap<>();
        params.put("users",users);
        params.put("查询成功",1);
        return params;
    }
    @RequestMapping(value ="/auditUser",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object>auditUser(){
        List<User>users=userService.auditUser();
        Map<String,Object>params=new HashMap<>();
        params.put("users",users);
        params.put("查询成功",1);
        return params;
    }
    @RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Map<String,Object>login(HttpServletRequest request, HttpServletResponse response,@RequestBody User userparam){
        Map<String,Object>param=new HashMap<>();
        //获取到该用户的session
        Subject subject = SecurityUtils.getSubject();
        String cnName= userparam.getCnName();
        String password=userparam.getPassword();
        //用于账号和密码
        UsernamePasswordToken token = new UsernamePasswordToken(cnName,password);
        try {
            subject.login(token);
            param.put("code", 200);
            param.put("msg", "请求成功");
            User user = userService.selectpw(cnName);
            user.setPassword("");
            param.put("data", user);
            ServletContext application = request.getServletContext();
            application.setAttribute("user",user);
           /* response.addHeader("Access-Control-Allow-Origin", "*");*/
        } catch (AuthenticationException e) {
            e.printStackTrace();
            param.put("code", 0);
        }
        return param;
    }
    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public Map<String,Object> logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        Map<String,Object>param=new HashMap<>();
        SecurityUtils.getSubject().logout();
        param.put("code", 200);
        param.put("msg", "安全退出");
        return param;
    }
}

