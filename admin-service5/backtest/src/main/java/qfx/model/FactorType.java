package qfx.model;

/**
 * Created by asus b250 on 2017/9/12.
 */
public class FactorType {
    private int id;
    private int typeId;
    private  String enName;
    private  String cnName;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "FactorType{" +
                "id=" + id +
                ", enName='" + enName + '\'' +
                ", cnName='" + cnName + '\'' +
                '}';
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

    public FactorType(int id, String cnName, String enName) {

        this.id = id;
        this.cnName = cnName;
        this.enName = enName;
    }
    public FactorType(){

    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
