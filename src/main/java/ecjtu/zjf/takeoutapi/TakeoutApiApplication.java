package ecjtu.zjf.takeoutapi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("ecjtu.zjf.takeoutapi.mapper")
public class TakeoutApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TakeoutApiApplication.class, args);
    }

}
