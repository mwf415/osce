package com.youyicn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.youyicn.shiro.MyFreeMarkerConfiger;

@Configuration
public class FreeMarkerConfig{
	
    @Value("${spring.freemarker.template-loader-path}")
    private String templateLoaderPath;
    
	@Bean
	public FreeMarkerConfigurer FreeMarkerConfigurer(){
		MyFreeMarkerConfiger freeMarkerConfiger = new MyFreeMarkerConfiger();
		freeMarkerConfiger.setTemplateLoaderPath(templateLoaderPath);
		return freeMarkerConfiger;
	}
	
}
