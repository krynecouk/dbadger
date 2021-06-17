package com.ataccama.dbadger.config.web;

import com.ataccama.dbadger.config.ds.DataSourceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final DataSourceInterceptor dataSourceInterceptor;

    @Autowired
    public WebConfig(DataSourceInterceptor dataSourceInterceptor) {
        this.dataSourceInterceptor = dataSourceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(dataSourceInterceptor).addPathPatterns(
                "/connections/*/metadata/**",
                "/connections/*/data/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
