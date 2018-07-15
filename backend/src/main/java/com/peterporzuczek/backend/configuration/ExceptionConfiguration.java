package com.peterporzuczek.backend.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
public class ExceptionConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment enviroment;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/400").setViewName("redirect:/" + enviroment.getProperty("error.404"));
        registry.addViewController("/401").setViewName("redirect:/" + enviroment.getProperty("error.401"));
        registry.addViewController("/403").setViewName("redirect:/" + enviroment.getProperty("error.402"));
        registry.addViewController("/404").setViewName("redirect:/" + enviroment.getProperty("error.403"));
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,
                    "/400"));
            container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED,
                    "/401"));
            container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,
                    "/403"));
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,
                    "/404"));
        };
    }
}
