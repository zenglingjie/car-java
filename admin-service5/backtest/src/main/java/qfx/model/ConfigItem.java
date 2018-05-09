package qfx.model;

/**
 * Created by asus b250 on 2017/9/21.
 */
public class ConfigItem {
    private  int id;
    private String cnName;
    private String enName;
    private int defaultValueId;

    @Override
    public String toString() {
        return "ConfigItem{" +
                "id=" + id +
                ", cnName='" + cnName + '\'' +
                ", enName='" + enName + '\'' +
                ", defaultValueId=" + defaultValueId +
                '}';
    }

    public int getDefaultValueId() {
        return defaultValueId;
    }

    public ConfigItem(int id, int defaultValueId, String enName, String cnName) {
        this.id = id;
        this.defaultValueId = defaultValueId;
        this.enName = enName;
        this.cnName = cnName;
    }

    public void setDefaultValueId(int defaultValueId) {
        this.defaultValueId = defaultValueId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public ConfigItem(int id, String enName, String cnName) {

        this.id = id;
        this.enName = enName;
        this.cnName = cnName;
    }

    public   ConfigItem(){

    }
}
