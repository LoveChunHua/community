package life.majiang.community.community.proider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.community.pojo.AccessTokenPojo;
import life.majiang.community.community.pojo.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by sunkai
 * Date 2019/12/15 18:06
 **/
@Component
public class GithubProvider {
    //用okhttp的POST请求提交accessTokenPojo获取acceessToken
    public String getAccessToken(AccessTokenPojo accessTokenPojo){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenPojo));//用JSON的格式处理类中的变量参数
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //用okhttp的Get请求获取用户信息
    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
