package life.majiang.community.community.service;

import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;
import life.majiang.community.community.pojo.QuestionPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunkai
 * Date 2019/12/24 17:10
 **/
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionPojo> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionPojo> questionPojoList = new ArrayList<>();
        for(Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionPojo questionPojo = new QuestionPojo();
            BeanUtils.copyProperties(question,questionPojo);//将question复制给questionPojo
            questionPojo.setUser(user);
            questionPojoList.add(questionPojo);
        }
        return questionPojoList;
    }
}
