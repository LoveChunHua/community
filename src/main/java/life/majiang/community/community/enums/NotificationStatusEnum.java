package life.majiang.community.community.enums;

/**
 * Created by sunkai
 * Date 2020/1/2 14:25
 **/
public enum NotificationStatusEnum {
    UNREAD(0),READ(1);
    private int status;

    NotificationStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
