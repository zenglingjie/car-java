package qfx.controller;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qfx.Util.MailTest;
import qfx.model.*;
import qfx.service.FactorTypeService;
import qfx.service.FactorsService;
import qfx.service.LogService;
import qfx.service.UserService;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by asus b250 on 2017/10/9.
 */
/*suditState
 1  是  2否
* 1   审核通过
* 3   待审核
* 2   重置状态
* */

/*setSuditPassState
* 1:增加审核
* 2:删除审核
* 3:重置审核*/
@Controller
@ResponseBody
@CrossOrigin(methods = {RequestMethod.POST,RequestMethod.GET},maxAge = 3600,allowCredentials = "true")
public class EmailController {
    @Autowired
    FactorsService factorService;
    @Autowired
    UserService userService;
    @Autowired
    LogService logService;
    @RequestMapping(value ="/sendEmail",method = {RequestMethod.POST,RequestMethod.GET})
    public void sendEmail(@RequestParam("id") int id, @RequestParam("cnName")String cnName){
        User  users=userService.selectpw(cnName);
        String  email=users.getEmail();
        Factors  factors=new Factors();
        factors.setId(id);
        factors.setUserCname(cnName);
        factorService.updateState(factors);
        try {
            MailTest mailTest=new MailTest();
            String addAudit="增加因子待审核";
            mailTest.mailTest(email,addAudit);
            //设置为待审核待审核
            factors.setAuditState(3);
            factors.setAuditStaNaFalse(1);
            factors.setAuditStaNaTrue(1);
            factors.setSuditPassState(1);
            factors.setIsValid(3);
            factorService.updateState(factors);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value ="/returnSendEmail",method = {RequestMethod.POST,RequestMethod.GET})
    public void returnSendEmail(@RequestParam("adminUser")String adminUser){
        try {
            MailTest mailTest=new MailTest();
            String addAuditPass="你新增的因子已经审核通过";
            mailTest.mailTest(adminUser,addAuditPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value ="/sendEmailDelete",method = {RequestMethod.POST,RequestMethod.GET})
    public void sendEmailDelete(@RequestParam("id") int id, @RequestParam("cnName")String cnName,@RequestParam("auditStaFalse")int auditStaFalse){
        User  users=userService.selectpw(cnName);
        String  email=users.getEmail();
        Factors  factors=new Factors();
        factors.setId(id);
        factors.setUserCname(cnName);
        factorService.updateState(factors);
        try {
            MailTest mailTest=new MailTest();
            String addAudit="删除因子待审核";
            mailTest.mailTest(email,addAudit);
            //设置为待审核待审核9
            factors.setAuditState(3);
            factors.setAuditStaNaFalse(1);
            factors.setAuditStaNaTrue(1);
            factors.setSuditPassState(2);
            if(auditStaFalse==1){
                factors.setIsValid(1);
            }else{
               factors.setIsValid(2);
            }
            factorService.updateState(factors);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value ="/returnDeleteSendEmail",method = {RequestMethod.POST,RequestMethod.GET})
    public void returnDeleteSendEmail(@RequestParam("adminUser")String adminUser){
        try {
            MailTest mailTest=new MailTest();
            String addAuditPass="你删除的因子已经审核通过";
            mailTest.mailTest(adminUser,addAuditPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value ="/sendEmailReset",method = {RequestMethod.POST,RequestMethod.GET})
    public void sendEmailReset(@RequestParam("id") int id, @RequestParam("cnName")String cnName,@RequestParam("auditResetStaFalse") int auditResetStaFalse){
        User  users=userService.selectpw(cnName);
        String  email=users.getEmail();
        Factors  factors=new Factors();
        factors.setId(id);
        factors.setUserCname(cnName);
        factorService.updateState(factors);
        try {
            MailTest mailTest=new MailTest();
            String addAudit="重置因子待审核";
            mailTest.mailTest(email,addAudit);
            //设置为待审核待审核
            factors.setAuditState(3);
            factors.setAuditStaNaFalse(1);
            factors.setAuditStaNaTrue(1);
            factors.setSuditPassState(3);
            if(auditResetStaFalse==1){
                factors.setIsValid(1);
            }else{
                factors.setIsValid(2);
            }
            int  aa=factors.getIsPassAudit();
            factorService.updateState(factors);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value ="/returnResetSendEmail",method = {RequestMethod.POST,RequestMethod.GET})
    public void returnResetSendEmail(@RequestParam("adminUser")String adminUser){
        try {
            MailTest mailTest=new MailTest();
            String addAuditPass="你重置的因子已经审核通过";
            mailTest.mailTest(adminUser,addAuditPass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //新增审核通过
    @RequestMapping(value ="/auditpass",method = {RequestMethod.POST,RequestMethod.GET})
    public void auditpass(@RequestBody Factors factors){
            //为审核通过
            factors.setAuditState(1);
            factors.setAuditStaNaFalse(1);
            factors.setAuditStaNaTrue(0);
            //审核通过
            factors.setIsValid(1);
            factors.setIsPassAudit(1);
            factorService.updateState(factors);
    }
    //删除审核通过
    @RequestMapping(value ="/Deleteauditpass",method = {RequestMethod.POST,RequestMethod.GET})
    public void Deleteauditpass(@RequestBody Factors factors){
        //为审核通过
        factors.setAuditState(1);
        factors.setAuditStaNaFalse(1);
        factors.setAuditStaNaTrue(0);
        factorService.updateState(factors);
        factorService.deleteFactors(factors.getId());
    }


    @RequestMapping(value ="/Resetauditpass",method = {RequestMethod.POST,RequestMethod.GET})
    public void Resetauditpass(@RequestBody Factors factors,HttpServletRequest request){
        String enName=factors.getEnName();
        factorService.deleteAlname(enName);
        //接口状态无效
        factors.setIsValid(2);
        //重置2
        factors.setAuditState(2);
        factorService.updateState(factors);
        ServletContext application = request.getServletContext();
        User  user=(User)application.getAttribute("user");
        String  userName=user.getCnName();
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        Log log=new Log();
        log.setName(userName);
        log.setText("FACTOR RESET");
        log.setUpdateTime(dateString);
        logService.addLog(log);
    }

    //下线审核不通过
    @RequestMapping(value ="/auditnotPass",method = {RequestMethod.POST,RequestMethod.GET})
    public void auditnotPass(@RequestBody Factors factors,HttpServletRequest request){
       try{
        String enName=factors.getEnName();
        factors.setIsValid(2);
        factors.setAuditStaNaTrue(1);
        factors.setAuditStaNaFalse(0);
        factorService.updateState(factors);
        String adminUser=factors.getAdminUser();
        MailTest mailTest=new MailTest();
        String addAuditPass="你提交的因子审核不通过";
        mailTest.mailTest(adminUser,addAuditPass);
       }catch (Exception e) {
           e.printStackTrace();
       }

    }


    //上线审核不通过
    @RequestMapping(value ="/auditnotPass1",method = {RequestMethod.POST,RequestMethod.GET})
    public void auditnotPass1(@RequestBody Factors factors,HttpServletRequest request){
       try{ String enName=factors.getEnName();
        factors.setIsValid(1);
        factors.setAuditStaNaTrue(0);
        factors.setAuditStaNaFalse(1);
        factorService.updateState(factors);
        String adminUser=factors.getAdminUser();
        MailTest mailTest=new MailTest();
        String addAuditPass="你提交的因子审核不通过";
        mailTest.mailTest(adminUser,addAuditPass);
    }catch (Exception e) {
        e.printStackTrace();
    }

    }

   /* @RequestMapping(value ="/addFactors",method = {RequestMethod.POST,RequestMethod.GET})
    public void addFactorss(@RequestBo.dy Factors factors, HttpServletRequest request, HttpServletResponse response){
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = request.getServletContext().getRealPath("/resources/webapp/uploads");
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            System.out.println(savePath+"目录不存在，需要创建");
            //创建目录
            file.mkdir();
        }
        //消息提示
        String message = "";
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            // 1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item :list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    message = "文件上传成功！";
                }
            }
        }catch (Exception e) {
            message= "文件上传失败！";
            e.printStackTrace();

        }
        String cnName=factors.getCnName();
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
        String  factorValue=null;
        for(int z=0;z<strArray.length;z++){
            factorValue= strArray[z];
        }
        //因子类型id
        int typeId= factorTypeService.selectId(cnName);
        factors.setTypeId(typeId);
        factors.setValid(0);
        factors.setIndexEnable(0);
        factors.setLoadEnable(0);
        factorsService.addFactors(factors);
        int factorId=factors.getId();
        Operators operatorss=new Operators();
        operatorss.setFactorId(factorId);
        operatorss.setCnName("11");
        operatorss.setEnName("11");
        operatorss.setIsDefault(0);
        operatorsService.addOperators(operatorss);

    }*/    }


