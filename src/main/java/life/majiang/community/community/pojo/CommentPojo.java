package life.majiang.community.community.pojo;

import lombok.Data;

/**
 * Created by sunkai
 * Date 2019/12/30 17:14
 **/
@Data
public class CommentPojo {
    private Long parentId;
    private String content;
    private Integer type;
}
