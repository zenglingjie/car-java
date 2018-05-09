package qfx.common.service.model;

import java.util.Date;
import java.util.List;

public class FactorReq {
    /** name: getfactor*/
    public String name;
    public List<String> codes;
    public Date date;
    public List<FactorParam> factors;
    public List<String> getCodes() {
        return codes;
    }
}
