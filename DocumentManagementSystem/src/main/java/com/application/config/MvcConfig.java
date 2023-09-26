package com.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private static final long MAX_UPLOAD_SIZE = 1000000000;

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new
                CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(MAX_UPLOAD_SIZE);
        return multipartResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/admin/admin_panel").setViewName("user/admin_panel");
        registry.addViewController("/specialist/specialist_panel").setViewName("specialist/specialist_panel");
        registry.addViewController("/head/head_panel").setViewName("head/head_panel");
        registry.addViewController("/head/send_assignmentToSpec").setViewName("head/send_assignment");
        registry.addViewController("/director/director_panel").setViewName("director/director_panel");
        registry.addViewController("/director/send_assignmentToHead").setViewName("director/send_assignmentToHead");
        registry.addViewController("/director/send_assignmentToSpec").setViewName("director/send_assignmentToSpec");
    }
}