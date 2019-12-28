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
        Integer totalPage;
        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //容错机制
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        pagintationPojo.setPagination(totalPage, page);
        
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

    public PagintationPojo list(Integer userId, Integer page, Integer size) {
        PagintationPojo pagintationPojo = new PagintationPojo();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        //容错机制
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        pagintationPojo.setPagination(totalPage, page);//将传入的记录总数，页面，每个页面展示的大小传入，调用该方法就能直接展示需要的页面

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
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

    public QuestionPojo getById(Integer id) {
        Question question =questionMapper.getById(id);
        QuestionPojo questionPojo = new QuestionPojo();
        BeanUtils.copyProperties(question,questionPojo);
        User user = userMapper.findById(question.getCreator());
        questionPojo.setUser(user);
        return questionPojo;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            questionMapper.create(question);
        }else{
            //更新
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
