package life.majiang.community.community.model;

import lombok.Data;

/**
 * Created by sunkai
 * Date 2019/12/17 21:05
 **/
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}