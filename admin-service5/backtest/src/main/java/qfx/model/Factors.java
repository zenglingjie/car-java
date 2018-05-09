package qfx.model;

import java.util.List;

/**
 * Created by asus b250 on 2017/9/14.
 * 选中的操作符
 */
public class Factors {
    private int id;
    private  String enName;
    private int typeId;
    private String cnName;
    private String preSet;
    private String parameters;



    private List<Operators> operators;
    private String  factorType;
    private String operaNames;
    private int  auditStaNaTrue;
    private int  auditStaNaFalse;
    private int suditPassState;
    private String userCname;
    private int indexEnable;



    private String description;
    private int loadEnable;
    private String filePath;
    private int isValid;
    private String defaultValue;
    private int auditState;
    private int isPassAudit;
    private String adminUser;
    private String isHotName;

    public String getIsHotName() {
        return isHotName;
    }

    public void setIsHotName(String isHotName) {
        this.isHotName = isHotName;
    }

    public int getIsHot() {
        return isHot;
    }

    public void setIsHot(int isHot) {
        this.isHot = isHot;
    }

    private int isHot;

    public String getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(String adminUser) {
        this.adminUser = adminUser;
    }

    public int getIsPassAudit() {
        return isPassAudit;
    }

    public void setIsPassAudit(int isPassAudit) {
        this.isPassAudit = isPassAudit;
    }

    public String getUserCname() {
        return userCname;
    }

    public void setUserCname(String userCname) {
        this.userCname = userCname;
    }

    public int getAuditStaNaTrue() {
        return auditStaNaTrue;
    }

    public void setAuditStaNaTrue(int auditStaNaTrue) {
        this.auditStaNaTrue = auditStaNaTrue;
    }

    public int getAuditStaNaFalse() {
        return auditStaNaFalse;
    }

    public void setAuditStaNaFalse(int auditStaNaFalse) {
        this.auditStaNaFalse = auditStaNaFalse;
    }

    public String getOperaNames() {
        return operaNames;
    }

    public void setOperaNames(String operaNames) {
        this.operaNames = operaNames;
    }

    public String getFactorType() {
        return factorType;
    }

    public void setFactorType(String factorType) {
        this.factorType = factorType;
    }

    public List<Operators> getOperators() {
        return operators;
    }

    public void setOperators(List<Operators> operators) {
        this.operators = operators;
    }

    public String getDescription() {
        return description;
    }

    public Factors(){

    }
    @Override
    public String toString() {
        return "FactorsMapper{" +
                "id=" + id +
                ", enName='" + enName + '\'' +
                ", typeId=" + typeId +
                ", cnName='" + cnName + '\'' +
                ", preSet='" + preSet + '\'' +
                ", parameters='" + parameters + '\'' +
                ", indexEnable=" + indexEnable +
                ", description='" + description + '\'' +
                ", loadEnable=" + loadEnable +
                ", filePath='" + filePath + '\'' +
                ", isValid=" + isValid +
                ", defaultValue='" + defaultValue + '\'' +
                ", auditState=" + auditState +
                '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLoadEnable() {
        return loadEnable;
    }

    public void setLoadEnable(int loadEnable) {
        this.loadEnable = loadEnable;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getAuditState() {
        return auditState;
    }

    public void setAuditState(int auditState) {
        this.auditState = auditState;
    }

    public int getIndexEnable() {
        return indexEnable;
    }

    public void setIndexEnable(int indexEnable) {
        this.indexEnable = indexEnable;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getPreSet() {
        return preSet;
    }

    public void setPreSet(String preSet) {
        this.preSet = preSet;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuditPassState() {
        return suditPassState;
    }

    public void setSuditPassState(int suditPassState) {
        this.suditPassState = suditPassState;
    }

    public Factors(int id, String enName, int typeId, String cnName, String preSet, String parameters, List<Operators> operators, String factorType, String operaNames, int auditStaNaTrue, int auditStaNaFalse, int suditPassState, String userCname, int indexEnable, String description, int loadEnable, String filePath, int isValid, String defaultValue, int auditState, int isPassAudit, String adminUser, String isHotName, int isHot) {
        this.id = id;
        this.enName = enName;
        this.typeId = typeId;
        this.cnName = cnName;
        this.preSet = preSet;
        this.parameters = parameters;
        this.operators = operators;
        this.factorType = factorType;
        this.operaNames = operaNames;
        this.auditStaNaTrue = auditStaNaTrue;
        this.auditStaNaFalse = auditStaNaFalse;
        this.suditPassState = suditPassState;
        this.userCname = userCname;
        this.indexEnable = indexEnable;
        this.description = description;
        this.loadEnable = loadEnable;
        this.filePath = filePath;
        this.isValid = isValid;
        this.defaultValue = defaultValue;
        this.auditState = auditState;
        this.isPassAudit = isPassAudit;
        this.adminUser = adminUser;
        this.isHotName = isHotName;
        this.isHot = isHot;
    }
}
