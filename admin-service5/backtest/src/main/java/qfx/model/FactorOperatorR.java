package qfx.model;

/**
 * Created by asus b250 on 2017/9/13.
 */
public class FactorOperatorR {
    private  int  id;
    private int  factorID;
    private int operatorID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(int operatorID) {
        this.operatorID = operatorID;
    }

    public int getFactorID() {
        return factorID;
    }

    public void setFactorID(int factorID) {
        this.factorID = factorID;
    }

    public FactorOperatorR(int id, int factorID, int operatorID) {

        this.id = id;
        this.factorID = factorID;
        this.operatorID = operatorID;
    }

    public FactorOperatorR(){

    }
}
