package ecjtu.zjf.takeoutapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("ecjtu.zjf.takeoutapi.mapper")
@EnableSwagger2
public class TakeoutApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakeoutApiApplication.class, args);
    }

}
