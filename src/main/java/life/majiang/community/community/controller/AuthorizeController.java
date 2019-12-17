package life.majiang.community.community.controller;

import life.majiang.community.community.pojo.AccessTokenPojo;
import life.majiang.community.community.pojo.GithubUser;
import life.majiang.community.community.proider.GithubProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request){
        AccessTokenPojo accessTokenPojo = new AccessTokenPojo();
        accessTokenPojo.setClient_id(clientId);
        accessTokenPojo.setClient_secret(clientSecret);
        accessTokenPojo.setCode(code);
        accessTokenPojo.setRedirect_uri(redirectUri);
        accessTokenPojo.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenPojo);
        GithubUser user = githubProvider.getUser(accessToken);

        if(user!=null){
            //登陆成功，写cookie和session
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else{
            //登陆失败，重新登录
            return "redirect:/";
        }
        //System.out.println(user.getName());//打印
        //return "index";
    }


}
