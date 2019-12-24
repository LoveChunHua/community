package life.majiang.community.community.model;

import lombok.Data;

/**
 * Created by sunkai
 * Date 2019/12/21 18:21
 **/
@Data
public class Question {
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
}

