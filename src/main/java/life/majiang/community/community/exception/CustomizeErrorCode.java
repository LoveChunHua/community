package life.majiang.community.community.exception;

/**
 * Created by sunkai
 * Date 2019/12/29 18:41
 **/
public enum CustomizeErrorCode implements ICustmizeErrorCode{
    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个问题试试");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
