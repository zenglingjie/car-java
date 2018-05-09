package qfx.common.api;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Setter
@Getter
@ToString
@NoArgsConstructor
@XmlRootElement
public class DataResult<T> implements Serializable {
    public DataResult(T data) {
        this.data = data;
        this.isSuccess = true;
    }

    public DataResult(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.isSuccess = false;
    }

    public DataResult(String errorCode, String errorDesc, long elapsedMilliseconds) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
        this.isSuccess = false;
        this.elapsedMilliseconds = elapsedMilliseconds;
    }

    /**
     * 是否处理成功
     */
    private boolean isSuccess;

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 错误代码
     */
    private String errorCode;

    /**
     * 错误描述
     */
    private String errorDesc;

    /**
     * 处理耗时(毫秒)
     */
    private long elapsedMilliseconds;

}
