package life.majiang.community.community.controller;

import life.majiang.community.community.mapper.CommentMapper;
import life.majiang.community.community.model.Comment;
import life.majiang.community.community.pojo.CommentPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sunkai
 * Date 2019/12/30 17:05
 **/
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentPojo commentPojo){//这个注解直接用JSON的格式将携带的值复制给commentPojo
        Comment comment = new Comment();
        comment.setParentId(commentPojo.getParentId());
        comment.setContent(commentPojo.getContent());
        comment.setType(commentPojo.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(1);
        commentMapper.insertSelective(comment);
        return null;
    }

}
