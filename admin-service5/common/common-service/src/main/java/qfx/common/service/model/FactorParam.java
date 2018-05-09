package qfx.common.service.model;

import java.util.Date;
import java.util.Map;

public class FactorParam {
    public String name;
    /**证券代码*/
    public String code;
    /**证券起始日期*/
    public Date date;
    public Map<String, Object> result;
    public Map<String, String> params;
}
