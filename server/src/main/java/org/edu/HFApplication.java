package org.edu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author :zzd
 * @date : 2023/1/3
 */
@SpringBootApplication
@MapperScan("org.edu.mapper")
@EnableSwagger2WebMvc
public class HFApplication {
    public static void main(String[] args) {
        SpringApplication.run(HFApplication.class, args);
    }
}
