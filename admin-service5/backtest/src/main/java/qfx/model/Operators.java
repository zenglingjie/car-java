package qfx.model;

/**
 * Created by asus b250 on 2017/9/13.
 */
public class Operators {
    private int id;
    private String name;



    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int factorId;
    private String enName;
    private String cnName;
    private int isDefault;


    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "Operators{" +
                "id=" + id +
                ", factorId=" + factorId +
                ", enName='" + enName + '\'' +
                ", cnName='" + cnName + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

    public int getFactorId() {
        return factorId;
    }

    public void setFactorId(int factorId) {
        this.factorId = factorId;
    }

    public Operators(int id, int isDefault, String cnName, String enName, int factorId) {
        this.id = id;
        this.isDefault = isDefault;

        this.cnName = cnName;
        this.enName = enName;
        this.factorId = factorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Operators(int id, String cnName, String enName) {

        this.id = id;
        this.cnName = cnName;
        this.enName = enName;
    }

    public Operators(){

    }
}
