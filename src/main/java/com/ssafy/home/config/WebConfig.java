package com.ssafy.home.config;

import com.ssafy.home.interceptor.MemberInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final MemberInterceptor memberInterceptor;

    private static final List<String> interceptorUrlPatterns = Arrays.asList("/user/*", "/board/*");
    private static final List<String> excludeInterceptorUrlPatterns = Arrays.asList("/user/login", "/user/register", "/house/*");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns(interceptorUrlPatterns)
                .excludePathPatterns(excludeInterceptorUrlPatterns);
    }
}
