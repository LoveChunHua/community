package life.majiang.community.community.controller;

import life.majiang.community.community.pojo.FilePojo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by sunkai
 * Date 2020/1/2 20:05
 **/
@Controller
public class FileController {
    @RequestMapping("/file/upload")
    @ResponseBody
    public FilePojo upload(){
        FilePojo filePojo = new FilePojo();
        filePojo.setSuccess(1);
        filePojo.setUrl("/images/wechat.png");
        return  filePojo;
    }
}
