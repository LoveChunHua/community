package life.majiang.community.community.mapper;

import life.majiang.community.community.model.Comment;
import life.majiang.community.community.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}