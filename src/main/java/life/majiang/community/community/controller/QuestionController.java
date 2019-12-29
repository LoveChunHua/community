package life.majiang.community.community.controller;

import life.majiang.community.community.pojo.QuestionPojo;
import life.majiang.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by sunkai
 * Date 2019/12/28 15:43
 **/
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        QuestionPojo questionPojo =questionService.getById(id);
        questionService.incView(id);
        model.addAttribute("question",questionPojo);
        return "question";
    }
}
