package com.bhurb.payments;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationWebMvcConfigurer {
    @Bean
    public WebMvcConfigurer webMvcConfiguration() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(final CorsRegistry corsRegistry) {
                corsRegistry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "/")
                        .allowedHeaders("*")
                        .allowedMethods("*")
                        .exposedHeaders("Location");
            }

            @Override
            public void addViewControllers(final ViewControllerRegistry viewControllerRegistry) {
                viewControllerRegistry.addViewController("/notFound")
                        .setViewName("forward:/index.html");
            }

            @Override
            public void addResourceHandlers(final ResourceHandlerRegistry resourceHandlerRegistry) {
                resourceHandlerRegistry.addResourceHandler("swagger-ui.html")
                        .addResourceLocations("classpath:/META-INF/resources/");

                resourceHandlerRegistry.addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");
            }

        };
    }

    @Bean
    public ServletWebServerFactory webServerFactoryCustomizer() {
        var tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
        var errorPage = new ErrorPage(HttpStatus.NOT_FOUND,
                "/notFound");
        tomcatServletWebServerFactory.addErrorPages(errorPage);
        return tomcatServletWebServerFactory;
    }

}
