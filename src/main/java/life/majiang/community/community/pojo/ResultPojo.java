package life.majiang.community.community.pojo;

import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import lombok.Data;

/**
 * Created by sunkai
 * Date 2019/12/30 17:50
 **/
@Data
public class ResultPojo<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultPojo errorOf(Integer code,String message){
        ResultPojo resultPojo = new ResultPojo();
        resultPojo.setCode(code);
        resultPojo.setMessage(message);
        return resultPojo;
    }

    public static ResultPojo errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(),errorCode.getMessage());
    }

    public static ResultPojo errorOf(CustomizeException e) {
        return errorOf(e.getCode(),e.getMessage());
    }

    public static ResultPojo okOf(){
        ResultPojo resultPojo = new ResultPojo();
        resultPojo.setCode(200);
        resultPojo.setMessage("请求成功");
        return resultPojo;
    }

    public static <T> ResultPojo okOf(T t){
        ResultPojo resultPojo = new ResultPojo();
        resultPojo.setCode(200);
        resultPojo.setMessage("请求成功");
        resultPojo.setData(t);
        return resultPojo;
    }
}
