package life.majiang.community.community.cache;

import life.majiang.community.community.pojo.TagPojo;
import org.apache.commons.lang3.StringUtils;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sunkai
 * Date 2020/1/1 18:27
 **/
public class TagCache {
    public static List<TagPojo> get(){
        List<TagPojo> tagpojos = new ArrayList<>();
        TagPojo program = new TagPojo();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","css","html","java","node"));
        tagpojos.add(program);

        TagPojo framework = new TagPojo();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","express","django","flask","koa","struts"));
        tagpojos.add(framework);

        TagPojo server = new TagPojo();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","unix","hadoop","tomcat","centos"));
        tagpojos.add(server);

        TagPojo db = new TagPojo();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","h2","redis","oracle","koa","struts"));
        tagpojos.add(db);
        return tagpojos;
    }

    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagPojo> tagPojos = get();
        List<String> tagList = tagPojos.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
