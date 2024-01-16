package com.econovation.rere.config;

import com.econovation.rere.interceptor.CUDLoginInterceptor;
import com.econovation.rere.interceptor.ReadLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CUDLoginInterceptor cudloginInterceptor;

    @Autowired
    private ReadLoginInterceptor readLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cudloginInterceptor)
                .order(1)
                .addPathPatterns("/cardbook/**")
                .excludePathPatterns(
                        "/users/login-id/check",
                        "/users//nickname/check",
                        "/users/signup",
                        "/users/login",
                        "/users/logout");

        registry.addInterceptor(readLoginInterceptor)
                .order(2)
                .addPathPatterns("/cardbook/{cardbookId}/themes/**")
                .addPathPatterns("/cardbook/{cardbookId}/theme/**");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5174") //withCredential이 true일 경우 와일드카드 안됨, 사용자가 접속한 url 기준(프론트 서버가 로컬이면 localhost)
                .allowedOrigins("null")
//                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
