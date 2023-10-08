package com.clic.ccdbaas.config;

import com.clic.ccdbaas.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //默认对所有请求进行拦截,对login和静态页面不拦截
        registry.addInterceptor(loginInterceptor)
                //.excludePathPatterns("/**","/static/**")
               // .addPathPatterns("/v1/record/toOut/allModifyRecord")
                .addPathPatterns("/v1/record/toOut/**")
                .addPathPatterns("/**/toOut/**")
                .addPathPatterns("/v1/**/open/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
