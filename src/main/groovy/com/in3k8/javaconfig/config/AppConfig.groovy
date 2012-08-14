package com.in3k8.javaconfig.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import com.in3k8.javaconfig.resolvers.OptionalRequestBodyMethodProcessor

@Configuration
@ComponentScan(basePackages = 'com.in3k8.javaconfig')
@EnableWebMvc
public class AppConfig extends WebMvcConfigurationSupport {

    @Bean Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create()
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        OptionalRequestBodyMethodProcessor processor = new OptionalRequestBodyMethodProcessor(this.messageConverters)

        argumentResolvers.add(processor)
    }

}