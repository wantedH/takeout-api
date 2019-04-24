package ecjtu.zjf.takeoutapi.configure;

import ecjtu.zjf.takeoutapi.common.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class PicLocationConfig implements WebMvcConfigurer {

    @Value("${picture.path}")
    String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**").addResourceLocations("file:"+path);


    }
}

