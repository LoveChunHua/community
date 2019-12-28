package life.majiang.community.community.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunkai
 * Date 2019/12/25 21:57
 **/
@Data
public class PagintationPojo {
    List<QuestionPojo> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;//赋值，在index中用到 th:class="${pagination.page==page}?'active':''"
        pages.add(page);
        for(int i =1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }

        //是否展示上一页的箭头
        if (page == 1) {
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totalPage) {
            showNext = false;
        }else {
            showNext = true;
        }
        //是否展示第一页
        if(pages.contains(1)) {
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if(pages.contains(totalPage)) {
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
