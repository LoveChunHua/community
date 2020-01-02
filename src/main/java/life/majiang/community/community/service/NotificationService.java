package life.majiang.community.community.service;

import life.majiang.community.community.enums.NotificationStatusEnum;
import life.majiang.community.community.enums.NotificationTypeEnum;
import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.mapper.NotificationMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.Notification;
import life.majiang.community.community.model.NotificationExample;
import life.majiang.community.community.model.User;
import life.majiang.community.community.model.UserExample;
import life.majiang.community.community.pojo.NotificationPojo;
import life.majiang.community.community.pojo.PagintationPojo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sunkai
 * Date 2020/1/2 15:28
 **/
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;

    public PagintationPojo list(Long userId, Integer page, Integer size) {
        PagintationPojo<NotificationPojo> pagintationPojo = new PagintationPojo<>();
        Integer totalPage;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int)notificationMapper.countByExample(notificationExample);
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
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if(notifications.size() ==0){
            return pagintationPojo;
        }
        List<NotificationPojo> notificationPojos = new ArrayList<>();

        for(Notification notification :notifications){
            NotificationPojo notificationPojo = new NotificationPojo();
            BeanUtils.copyProperties(notification,notificationPojo);
            notificationPojo.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationPojos.add(notificationPojo);
        }
        pagintationPojo.setData(notificationPojos);
        return pagintationPojo;
    }

    public Long unreadCount(Long userId) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationPojo read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if(notification.getReceiver() !=user.getId()){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());//标记为已读状态
        notificationMapper.updateByPrimaryKey(notification);
        NotificationPojo notificationPojo = new NotificationPojo();
        BeanUtils.copyProperties(notification,notificationPojo);
        notificationPojo.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));

        return notificationPojo;

    }
}
