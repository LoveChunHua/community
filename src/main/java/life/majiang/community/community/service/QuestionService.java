package life.majiang.community.community.service;

import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.User;
import life.majiang.community.community.pojo.PagintationPojo;
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
//service层组合了question和user
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PagintationPojo list(Integer page, Integer size) {
        PagintationPojo pagintationPojo = new PagintationPojo();
        Integer totalCount = questionMapper.count();
        pagintationPojo.setPagination(totalCount, page, size);//将传入的记录总数，页面，每个页面展示的大小传入，调用该方法就能直接展示需要的页面
        //容错机制
        if (page < 1) {
            page = 1;
        }
        if (page > pagintationPojo.getTotalPage()) {
            page = pagintationPojo.getTotalPage();
        }

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionPojo> questionPojoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionPojo questionPojo = new QuestionPojo();
            BeanUtils.copyProperties(question, questionPojo);//将question复制给questionPojo
            questionPojo.setUser(user);
            questionPojoList.add(questionPojo);
        }
        pagintationPojo.setQuestions(questionPojoList);

        return pagintationPojo;
    }
}
