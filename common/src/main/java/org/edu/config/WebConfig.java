package org.edu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web项目配置类
 * @author :zzd
 * @date : 2023/1/3
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // TODO 绝对路径改成你电脑上的
        registry.addResourceHandler("/image/**").addResourceLocations("file:C:\\Users\\10311\\Desktop\\Huaxiamall\\static\\image\\");
    }
}
