package ru.bychkov.springequation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final String baseApiPath;

    public WebConfig(@Value("${api-path}") String baseApiPath) {
        this.baseApiPath = baseApiPath;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(baseApiPath, HandlerTypePredicate.forBasePackage("ru.bychkov.springequation.controller"));
    }

}
