package com.youyicn.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.UUID;


@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/src/main/webapp/**").addResourceLocations("classpath:/webapp/");
        super.addResourceHandlers(registry);
    }

//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new ResponseResultInterceptor());
//    }


    public static void main(String[] args) {

        String fileName ="atext";
        if( fileName.indexOf(".") != -1){
            fileName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
            System.out.println(fileName);
        }else {
            fileName= UUID.randomUUID().toString();
            System.out.println(fileName);
        }

    }
}
