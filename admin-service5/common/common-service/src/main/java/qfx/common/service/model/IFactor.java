package qfx.common.service.model;


public interface IFactor<T>  {
    /** 因子含参数的名称*/
    String getName(int idx);
    /** 取因子的计算响应*/
    void getRsp(FactorReq factorReq, FactorRsp factorRsp, FactorParam factorParam);
}
