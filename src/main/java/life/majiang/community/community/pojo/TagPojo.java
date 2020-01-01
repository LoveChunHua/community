package life.majiang.community.community.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by sunkai
 * Date 2020/1/1 18:28
 **/
@Data
public class TagPojo {
    private String categoryName;
    private List<String> tags;
}
