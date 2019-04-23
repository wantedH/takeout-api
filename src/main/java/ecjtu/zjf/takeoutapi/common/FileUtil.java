package ecjtu.zjf.takeoutapi.common;


import org.apache.tomcat.jni.FileInfo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class FileUtil {
    private static final String folder=System.getProperty("user.dir")+"/src/main/resources/static/pic";

    public static String TransformFile(MultipartFile file) throws IOException {
        String[] words=file.getOriginalFilename().split("\\.");
        String name=new Date().getTime()+"."+words[words.length-1];
        File localFile = new File(folder,name);
        name="pic/"+name+'?'+ UUID.randomUUID();
        file.transferTo(localFile);
        return name;
    }
}
