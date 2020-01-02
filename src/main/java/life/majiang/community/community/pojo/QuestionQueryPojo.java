package life.majiang.community.community.pojo;

import lombok.Data;

/**
 * Created by sunkai
 * Date 2020/1/2 21:20
 **/
@Data
public class QuestionQueryPojo {
    private String search;
    private Integer page;
    private Integer size;
}
