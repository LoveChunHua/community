package life.majiang.community.community.pojo;

import lombok.Data;

/**
 * Created by sunkai
 * Date 2019/12/15 18:45
 **/
@Data
public class AccessTokenPojo {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}

