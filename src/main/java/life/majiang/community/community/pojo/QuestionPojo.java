package life.majiang.community.community.pojo;

import life.majiang.community.community.model.User;
import lombok.Data;

/**
 * Created by sunkai
 * Date 2019/12/24 17:07
 **/
@Data
public class QuestionPojo {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
