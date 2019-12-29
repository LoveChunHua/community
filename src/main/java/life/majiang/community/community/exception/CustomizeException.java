package life.majiang.community.community.exception;

/**
 * Created by sunkai
 * Date 2019/12/29 18:22
 **/
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustmizeErrorCode errorCode) {
        this.message = errorCode.getMessage();//errocode获取到枚举中的错误信息
    }

    @Override
    public String getMessage() {
        return message;
    }
}
