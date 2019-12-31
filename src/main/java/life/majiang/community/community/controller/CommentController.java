package life.majiang.community.community.controller;

import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.model.Comment;
import life.majiang.community.community.model.User;
import life.majiang.community.community.pojo.CommentCreatePojo;
import life.majiang.community.community.pojo.ResultPojo;
import life.majiang.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunkai
 * Date 2019/12/30 17:05
 **/
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreatePojo commentCreatePojo,
                       HttpServletRequest request){//这个注解直接用JSON的格式将携带的值复制给commentPojo

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultPojo.errorOf(CustomizeErrorCode.NO_LOGIN);
        }


        Comment comment = new Comment();
        comment.setParentId(commentCreatePojo.getParentId());
        comment.setContent(commentCreatePojo.getContent());
        comment.setType(commentCreatePojo.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultPojo.okOf();
    }

}
