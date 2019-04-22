package ecjtu.zjf.takeoutapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("ecjtu.zjf.takeoutapi.mapper")
@EnableSwagger2
@EnableAsync
@EnableCaching
public class TakeoutApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakeoutApiApplication.class, args);
    }

}
