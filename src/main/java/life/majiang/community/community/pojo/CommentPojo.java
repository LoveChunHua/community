package life.majiang.community.community.pojo;

import life.majiang.community.community.model.User;
import lombok.Data;

/**
 * Created by sunkai
 * Date 2019/12/31 15:13
 **/
@Data
public class CommentPojo {
    private Integer commentCount;
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;
}
