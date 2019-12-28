package life.majiang.community.community.controller;

import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import life.majiang.community.community.pojo.AccessTokenPojo;
import life.majiang.community.community.pojo.GithubUser;
import life.majiang.community.community.proider.GithubProvider;

import life.majiang.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


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

    @Autowired(required=false)
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletResponse response){
        AccessTokenPojo accessTokenPojo = new AccessTokenPojo();
        accessTokenPojo.setClient_id(clientId);
        accessTokenPojo.setClient_secret(clientSecret);
        accessTokenPojo.setCode(code);
        accessTokenPojo.setRedirect_uri(redirectUri);
        accessTokenPojo.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenPojo);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser!=null && githubUser.getId() !=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            //登陆成功，写cookie和session
            //request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            //登陆失败，重新登录
            return "redirect:/";
        }
        //System.out.println(user.getName());//打印
        //return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}
