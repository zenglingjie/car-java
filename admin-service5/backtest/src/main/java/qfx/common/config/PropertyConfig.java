package qfx.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix="custumProp") //application.yml中的myProps下的属性

public class PropertyConfig {
    private String loginUrl;
    private String runCmd;
    private boolean isDev;
    private String[] arrayProp;
    private List<Map<String, String>> listProp = new ArrayList<>(); //接收prop1里面的属性值
    private Map<String, String> mapProp = new HashMap<>(); //接收prop1里面的属性值
    private Map<String, String> mapCodeSave = new HashMap<>(); //接收Python code转换属性


    public boolean getIsDev() {
        return isDev;
    }

    public void setIsDev(boolean isDev) {
        this.isDev = isDev;
    }

    public void setListProp(List<Map<String, String>> listProp) {
        this.listProp = listProp;
    }

    public Map<String, String> getMapCodeSave() {
        return mapCodeSave;
    }

    public void setMapCodeSave(Map<String, String> mapCodeSave) {
        this.mapCodeSave = mapCodeSave;
    }


    public String getRunCmd() {
        return runCmd;
    }

    public void setRunCmd(String runCmd) {
        this.runCmd = runCmd;
    }
    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public List<Map<String, String>> getListProp() {
        return listProp;
    }
    public String[] getArrayProp() {
        return arrayProp;
    }

    public void setArrayProp(String[] arrayProp) {
        this.arrayProp = arrayProp;
    }

    public Map<String, String> getMapProp() {
        return mapProp;
    }

    public void setMapProp(Map<String, String> mapProp) {
        this.mapProp = mapProp;
    }
}
