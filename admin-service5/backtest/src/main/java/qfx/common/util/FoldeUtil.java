package qfx.common.util;

import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Base64;

public class FoldeUtil {

    // 以下为创建文件以及编码相关的方法
    public static String getPythonHome() {
        return "e:/python/";
    }

    // 获取当前用户使用的脚本名称
    public static String getPythonFileName(HttpServletRequest request) {
        String id = request.getParameter("id");
        String uname = WebHelper.getLoginPin();
        String fileName = "strategy";
        if (StringUtils.isNotBlank(uname)) {
            String temp = uname + (StringUtils.isNotBlank(id) ? id : "0");
            try {
                fileName = Base64.getEncoder().encodeToString(temp.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    // 获取当前用户使用的脚本名称
    public static String getPythonFileName(int id, String uname) {
        // String uname = WebHelper.getPin();
        String fileName = "strategy";
        if (StringUtils.isNotBlank(uname)) {
            String temp = uname + id;
            try {
                fileName = Base64.getEncoder().encodeToString(temp.getBytes("UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    // 获取当前用户使用的脚本的目录
    public static String getAndCreatePythonFolder() {
        String folderName = getPythonFolder();
        if (StringUtils.isNotBlank(folderName)) {
            File folder = new File(getPythonHome() +  folderName);
            if (!folder.exists() || !folder.isDirectory()) {
                if (!folder.mkdir()) {
                    return "";
                }
            }
        }
        return folderName;
    }

    // 获取当前用户使用的脚本的目录
    public static String getPythonFolder() {
        return "test";
        /*String folderName = null;
        try {
            folderName = Base64.getEncoder().encodeToString(WebHelper.getLoginPin().getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folderName;*/
    }

    // 获取当前用户使用的脚本的目录
    public static String getPythonFolder(String username) {
        String folderName = null;
        try {
            folderName = Base64.getEncoder().encodeToString(username.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return folderName;
    }

    public static String buildPythonFile(HttpServletRequest request, String codeStr) {
        String fileName = getPythonFileName(request);
        String folderName = getAndCreatePythonFolder();

        if (StringUtils.isBlank(fileName) || StringUtils.isBlank(folderName)) {
            return null;
        }
        String fullPathName = getPythonHome() + folderName + "/" + fileName + ".py";

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fullPathName));
            bw.write(codeStr);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullPathName;
    }

}
