package life.majiang.community.community.controller;

import life.majiang.community.community.enums.CommentTypeEnum;
import life.majiang.community.community.pojo.CommentPojo;
import life.majiang.community.community.pojo.QuestionPojo;
import life.majiang.community.community.service.CommentService;
import life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by sunkai
 * Date 2019/12/28 15:43
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        QuestionPojo questionPojo = questionService.getById(id);
        List<QuestionPojo> relatedQuestions = questionService.selectRelated(questionPojo);
        List<CommentPojo> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        questionService.incView(id);
        model.addAttribute("question", questionPojo);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        return "question";
    }
}
