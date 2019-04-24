package ecjtu.zjf.takeoutapi.common;


import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;
@Component
public class FileUtil {
    @Value("${picture.path}")
    String folder;

    @Async
    public void transformFile(MultipartFile file,String name) throws IOException {
        File localFile = new File(folder,name);
        file.transferTo(localFile);
    }

    @Async
    public void deletePicFile(String name){
        File delFile = new File(folder+name);
        if(delFile.isFile() && delFile.exists()) {
            delFile.delete();
        }
    }
}
