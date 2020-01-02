package life.majiang.community.community.controller;

import life.majiang.community.community.enums.NotificationTypeEnum;
import life.majiang.community.community.model.User;
import life.majiang.community.community.pojo.NotificationPojo;
import life.majiang.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunkai
 * Date 2020/1/2 16:51
 **/
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,
                          HttpServletRequest request){

        User user =(User)request.getSession().getAttribute("user");
        if(user ==null){
            return "redirect:/";
        }
        NotificationPojo notificationPojo =notificationService.read(id,user);

        if(NotificationTypeEnum.REPLY_COMMENT.getType() == notificationPojo.getType()
        || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationPojo.getType()){

            return "redirect:/question/" + notificationPojo.getOuterid();
        }else{
            return "redirect:/";
        }
    }
}
