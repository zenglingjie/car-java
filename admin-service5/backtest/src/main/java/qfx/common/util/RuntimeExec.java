package qfx.common.util;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import qfx.common.Const;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class RuntimeExec {

    private final static Logger LOG = Logger.getLogger(RuntimeExec.class);

    public static Map<String,String> excuteCmd(String cmd, WebSocketSession session, String SYSTEM_CODE, String os) {

        Process process = null;

        Map<String, String> result = new HashMap<String, String>();
        try {
            process = Runtime.getRuntime().exec(cmd);
            InputStreamReader ir;
            InputStreamReader irError;
            if (os.equals("win")) {
                ir = new InputStreamReader(process.getInputStream(), Charset.forName("GBK"));
                irError = new InputStreamReader(process.getErrorStream(), Charset.forName("GBK"));
            }else {
                 ir = new InputStreamReader(process.getInputStream());
                 irError = new InputStreamReader(process.getErrorStream());
            }
            LineNumberReader input = new LineNumberReader(ir);

            BufferedReader read = new BufferedReader(irError);
//            LineNumberReader errorInput = new LineNumberReader(irError);
            System.out.print(input);


            String line = "";
            String logs = "";
            while ((line = input.readLine()) != null) {
                System.out.print("line:   "+line);
                if(line.startsWith(Const.ROI_CURVE_TAG)){
                    String output = line;
                    String jsonStr = output.substring(output.indexOf('{'), output.length());
                    if(output != null) {
                        jsonStr = jsonStr.replace("NaN", "\"NaN\"");
                    }
                    JSONObject json = JSONObject.fromObject(jsonStr);
                    if(json.containsKey("type")){
                        String type = json.getString("type").trim();
                        if (type.equals("summary")) session.sendMessage(new TextMessage(jsonStr));
                        if(type.equals("curve")) {
                            session.sendMessage(new TextMessage(jsonStr));
                        }else{
                            String value = "\"" + json.getString("type") + "\":{" + "\"data\":" + json.getString("data") + "},";
                            result.put(json.getString("type"), value);
                        }
                    }
                }else{
                    String log = line.trim().replace("\\","/") + "\n";
                    logs += log;
                }
            }


            String errorLine = "";
            String errorLog = "";
            while ((errorLine = read.readLine()) != null){
                System.out.println(errorLine);
                errorLine=new String(errorLine.getBytes("UTF-8"));
                System.out.println(errorLine);
                String log = "ELOG" + errorLine.trim().replace("\\","/") + "\n";
                errorLog += log;
            }
            session.sendMessage(new TextMessage("LOGEEE" + logs));
            session.sendMessage(new TextMessage("ELOGEEE" + errorLog));
            result.put("logs", logs);
            result.put("errorLog",errorLog);

        } catch (Exception e) {
            LOG.error(cmd, e);
            return result;
        }
        return result;
    }

}
