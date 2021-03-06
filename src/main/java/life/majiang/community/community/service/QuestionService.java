package life.majiang.community.community.service;

import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.mapper.QuestionExtMapper;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Question;
import life.majiang.community.community.model.QuestionExample;
import life.majiang.community.community.model.User;
import life.majiang.community.community.pojo.PagintationPojo;
import life.majiang.community.community.pojo.QuestionPojo;
import life.majiang.community.community.pojo.QuestionQueryPojo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PagintationPojo list(String search,Integer page, Integer size) {
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }

        PagintationPojo pagintationPojo = new PagintationPojo();
        Integer totalPage;
        QuestionQueryPojo questionQueryPojo = new QuestionQueryPojo();
        questionQueryPojo.setSearch(search);
        Integer totalCount = questionExtMapper.countBySearch(questionQueryPojo);

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
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        questionQueryPojo.setSize(size);
        questionQueryPojo.setPage(offset);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryPojo);
        List<QuestionPojo> questionPojoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionPojo questionPojo = new QuestionPojo();
            BeanUtils.copyProperties(question, questionPojo);//将question复制给questionPojo
            questionPojo.setUser(user);
            questionPojoList.add(questionPojo);
        }
        pagintationPojo.setData(questionPojoList);

        return pagintationPojo;
    }

    public PagintationPojo list(Long userId, Integer page, Integer size) {
        PagintationPojo pagintationPojo = new PagintationPojo();
        Integer totalPage;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
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
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionPojo> questionPojoList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionPojo questionPojo = new QuestionPojo();
            BeanUtils.copyProperties(question, questionPojo);//将question复制给questionPojo
            questionPojo.setUser(user);
            questionPojoList.add(questionPojo);
        }
        pagintationPojo.setData(questionPojoList);

        return pagintationPojo;
    }

    public QuestionPojo getById(Long id) {
        Question question =questionMapper.selectByPrimaryKey(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionPojo questionPojo = new QuestionPojo();
        BeanUtils.copyProperties(question,questionPojo);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionPojo.setUser(user);
        return questionPojo;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
//            question.setId(question.getId());
//            question.setViewCount(0);
//            question.setCommentCount(0);
//            question.setLikeCount(0);
            questionMapper.insertSelective(question);
        }else{
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIdEqualTo(question.getId());
            int updated =questionMapper.updateByExampleSelective(updateQuestion, example);
            if(updated != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionPojo> selectRelated(QuestionPojo questionPojo) {
        if(StringUtils.isBlank(questionPojo.getTag())){
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(questionPojo.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(questionPojo.getId());
        question.setTag(regexpTag);
        List<Question> questions = questionExtMapper.selectRelated(question);

        List<QuestionPojo> questionPojoS= questions.stream().map(q -> {
            QuestionPojo questionpojo = new QuestionPojo();
            BeanUtils.copyProperties(q,questionpojo);
            return questionpojo;
        }).collect(Collectors.toList());
        return questionPojoS;
    }
}
