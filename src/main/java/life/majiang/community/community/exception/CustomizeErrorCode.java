package life.majiang.community.community.exception;

/**
 * Created by sunkai
 * Date 2019/12/29 18:41
 **/
public enum CustomizeErrorCode implements ICustmizeErrorCode{
    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不要换个问题试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论"),
    NO_LOGIN(2003,"当前操作未登录，请登陆后重试"),
    SYS_ERROR(2004,"你妈妈喊你回家吃饭，要不你吃完饭再来"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者为空"),
    COMMENT_NOT_FOUND(2006,"你回复的评论不存在");

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode(){
        return code;
    }
}
