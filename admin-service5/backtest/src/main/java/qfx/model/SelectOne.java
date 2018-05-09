package qfx.model;

/**
 * Created by asus b250 on 2017/9/29.
 */
public class SelectOne {
    private String cnName;
    private String enName;
    private String description;

    private String factorType;
    private int indexEnable;
    private int loadEnable;
    private int isValid;
    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getPrcnName() {
        return cnName;
    }

    @Override
    public String toString() {
        return "SelectOne{" +
                "prcnName='" + cnName + '\'' +
                ", enName='" + enName + '\'' +
                ", description='" + description + '\'' +
                ", factorType='" + factorType + '\'' +
                ", indexEnable=" + indexEnable +
                ", loadEnable=" + loadEnable +
                ", isValid=" + isValid +
                '}';
    }
    public SelectOne(){

    }
    public SelectOne(String prcnName, int isValid, int loadEnable, int indexEnable, String factorType, String description, String enName) {
        this.cnName = prcnName;
        this.isValid = isValid;
        this.loadEnable = loadEnable;
        this.indexEnable = indexEnable;
        this.factorType = factorType;
        this.description = description;
        this.enName = enName;
    }

    public void setPrcnName(String prcnName) {
        this.cnName = prcnName;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getFactorType() {
        return factorType;
    }

    public void setFactorType(String factorType) {
        this.factorType = factorType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public int getLoadEnable() {
        return loadEnable;
    }

    public void setLoadEnable(int loadEnable) {
        this.loadEnable = loadEnable;
    }

    public int getIndexEnable() {
        return indexEnable;
    }

    public void setIndexEnable(int indexEnable) {
        this.indexEnable = indexEnable;
    }

}
