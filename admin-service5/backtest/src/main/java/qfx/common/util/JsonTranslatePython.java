package qfx.common.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import qfx.common.Const;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * Created by win7 on 2017/7/25.
 */
public class JsonTranslatePython {

    private String codeStr;
    int type;


    public JsonTranslatePython(String codeStr, int type) {
        this.codeStr = codeStr;
        this.type = type;
    }

    private JSONObject translateJson(String codeJson){
        JSONObject json = JSONObject.fromObject(codeJson);
        JSONObject mapPython = new JSONObject();
        for( Object item : json.keySet()){
            if(item.toString() != null) {
                JSONArray itemMinJsonArray = new JSONArray();
                for (Object key : json.getJSONArray(item.toString())) {
                            if(key != null){
                                    JSONObject jsonKey = JSONObject.fromObject(key);

                                    JSONObject itemMinJson = new JSONObject();
                                    if(item.toString().equals("交易设置")){
                                        itemMinJson.put("benchmark",jsonKey.getString("liData1"));
                                        itemMinJson.put("quantity",jsonKey.getString("liData2"));
                                        itemMinJson.put("weight",jsonKey.getString("liData3"));
                                        itemMinJson.put("price",jsonKey.getString("liData4"));
                                        itemMinJson.put("freq",jsonKey.getString("liData5"));
                                        itemMinJson.put("span",jsonKey.getString("liData6"));
                                        itemMinJson.put("serial_number",jsonKey.getString("liData7"));
                                        mapPython.put("settings",itemMinJson);

                                    }else if(item.toString().equals("股票池")) {
                                        itemMinJson.put("index",jsonKey.getString("liData1"));
                                        itemMinJson.put("sector",jsonKey.getString("liData2"));
                                        itemMinJson.put("industry",jsonKey.getString("liData3"));
                                        itemMinJson.put("exchange",jsonKey.getString("liData4"));
                                        itemMinJson.put("margin",jsonKey.getString("liData5"));
                                        itemMinJson.put("ST",jsonKey.getString("liData6"));
                                        mapPython.put("pool",itemMinJson);

                                    }else if(item.toString().equals("大盘止损")) {
                                        JSONObject condJson = null;
                                        JSONObject paramJson = null;
                                        if(jsonKey.containsKey("liData1")) {
                                            itemMinJson.put("name", jsonKey.getString("liData1"));
                                            itemMinJson.put("index", jsonKey.getString("liData2"));
                                            condJson.put("sequence", jsonKey.getString("liData3"));
                                            condJson.put("range", jsonKey.getString("liData4"));
                                            condJson.put("weight", jsonKey.getString("liData5"));
                                            if(jsonKey.containsKey("liData6")) {
                                            paramJson.put("industry", jsonKey.getString("liData6"));
                                            }
                                            if(jsonKey.containsKey("liData7")) {
                                                paramJson.put("exchange", jsonKey.getString("liData7"));
                                            }
                                            if(jsonKey.containsKey("liData8")) {
                                                paramJson.put("margin", jsonKey.getString("liData8"));
                                            }
                                            itemMinJson.put("cond", condJson);
                                            itemMinJson.put("param", paramJson);
                                            itemMinJsonArray.add(itemMinJson);
                                        }

                                    }else{
                                        JSONObject condJson = new JSONObject();
                                        JSONObject paramJson = new JSONObject();
                                        if(jsonKey.containsKey("tdData1")) {
                                            itemMinJson.put("name", jsonKey.getString("tdData1"));
                                            condJson.put("sequence", jsonKey.getString("tdData2"));
                                            condJson.put("range", jsonKey.getString("tdData3"));
                                            condJson.put("weight", jsonKey.getString("tdData4"));
                                            if(jsonKey.containsKey("tdData5")) {
                                                paramJson.put("industry", jsonKey.getString("tdData5"));
                                            }
                                            if(jsonKey.containsKey("tdData6")) {
                                                paramJson.put("exchange", jsonKey.getString("tdData6"));
                                            }
                                            if(jsonKey.containsKey("tdData7")) {
                                                paramJson.put("margin", jsonKey.getString("tdData7"));
                                            }
                                            itemMinJson.put("cond", condJson);
                                            itemMinJson.put("param", paramJson);
                                            itemMinJsonArray.add(itemMinJson);
                                        }
                                    }

                            }

                }
                if(!(item.toString().equals("交易设置") || (item.toString().equals("股票池")))) {
                    String itemKeyMax = "";
                    switch (item.toString()) {
                        case "大盘止损":
                            itemKeyMax = "market_stop";
                            break;
                        case "个股止损":
                            itemKeyMax = "stoploss";
                            break;
                        case "排序方式":
                            itemKeyMax = "sort";
                            break;
                        case "选股条件":
                            itemKeyMax = "conditions";
                            break;
                        default:
                            break;

                    }

                    mapPython.put(itemKeyMax,itemMinJsonArray);

                }

            }else if(item.toString() != null){
               System.out.println("no code data !!!");
            }
        }

        return mapPython;
    }

    public String  sumJson(){
        JSONObject mapPythonCode = translateJson(codeStr);
        String pythonStr = Const.PY_STRAT + mapPythonCode.toString() + Const.PY_END;
        return pythonStr;
    }

    public String savePythonFile(String prefix, String suffix, String path){
        String sumCodeStr = "";
       if(type == 1){
           sumCodeStr = sumJson();
       }else{
           sumCodeStr = codeStr;
       }
       File tempFile = null;
       if(path == null){
           try {
               tempFile = File.createTempFile(prefix, suffix);
           } catch (IOException e) {
               e.printStackTrace();
           }
       }else{
           File dir = new File(path);
           if(!dir.exists()){
               if(!dir.mkdirs()){
                   System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");
                   return null;
               }
           }
           try {
               tempFile = File.createTempFile(prefix, suffix, dir);
           } catch (IOException e) {
               e.printStackTrace();
           }

       }
//         byte[] bt = sumCodeStr.getBytes();
        writeFile(sumCodeStr,tempFile);

        return tempFile.getAbsolutePath();
    }

    public String savePythonFile(String prefix, String suffix, String path,String saveFilesFlag){

        String sumCodeStr = "";
        if(type == 1){
            sumCodeStr = sumJson();
        }else{
            sumCodeStr = codeStr;
        }
        File tempFile = null;
        if(path == null){
            try {
                tempFile = File.createTempFile(prefix, suffix);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            File dir = new File(path);
            if(dir.exists() && saveFilesFlag.equals("true")){
                clearFiles(path);
            }else{
                if(!dir.exists()) {
                    if (!dir.mkdirs()) {
                        System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");
                        return null;
                    }
                }
            }
            try {
                tempFile = File.createTempFile(prefix, suffix, dir);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
//        byte[] bt = sumCodeStr.getBytes();
        writeFile(sumCodeStr,tempFile);
        return tempFile.getAbsolutePath();
    }


    private void writeFile(String sumCodeStr,File tempFile){
        try{
            FileOutputStream in = new FileOutputStream(tempFile);
            OutputStreamWriter out = new OutputStreamWriter(in,"UTF-8");
            try{
                out.write(sumCodeStr.toCharArray());
                out.flush();
                out.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void clearFiles(String path){
        File file = new File(path);
        if(file.exists() && file.isDirectory()){
            File[] files = file.listFiles();
            for(File itemFile:files){
                itemFile.delete();
            }
        }
    }
}

