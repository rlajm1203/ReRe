package com.econovation.rere.config;

import com.econovation.rere.interceptor.CUDLoginInterceptor;
import com.econovation.rere.interceptor.ReadLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
                .addPathPatterns(
                        "/cardbook/**",
                        "/users/update/**",
                        "/users/logout")
                .excludePathPatterns();

        registry.addInterceptor(readLoginInterceptor)
                .order(2)
                .addPathPatterns("/cardbook/{cardbookId}/themes/**")
                .addPathPatterns("/cardbook/{cardbookId}/theme/**");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https:econo-rere.store","https://re-re-xt9d.vercel.app/","null")//withCredential이 true일 경우 와일드카드 안됨, 사용자가 접속한 url 기준(프론트 서버가 로컬이면 localhost)
//                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(new CurrentUserArgumentResolver());
//    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserArgumentResolver());
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
