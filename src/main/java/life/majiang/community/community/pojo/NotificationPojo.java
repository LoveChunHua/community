package life.majiang.community.community.pojo;

import life.majiang.community.community.model.User;
import lombok.Data;

/**
 * Created by sunkai
 * Date 2020/1/2 15:25
 **/
@Data
public class NotificationPojo {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private String outerTitle;
    private Long outerid;
    private String typeName;
    private Integer type;
}
