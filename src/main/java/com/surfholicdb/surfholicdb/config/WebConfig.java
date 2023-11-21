package com.surfholicdb.surfholicdb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MainInterceptor())
                .excludePathPatterns("/css/**", "/images/**", "/js/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("http://marineweather.nmpnt.go.kr:8001/openWeatherNow.do?serviceKey=9ED3DA27-EE19-4CCB-9877-8F76C187B0AB&resultType=json&mmaf=101&mmsi=994401578,994401597")
                .allowedOrigins()
                .allowedOriginPatterns("http://localhost:8080", "http://marineweather.nmpnt.go.kr:8001/openWeatherNow.do?serviceKey=9ED3DA27-EE19-4CCB-9877-8F76C187B0AB&resultType=json&mmaf=101&mmsi=994401578,994401597")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(5000);
        // registry.addMapping("/**")
        //         .allowedOriginPatterns("http://localhost:8080")
        //         .allowedMethods("GET", "POST", "PUT", "DELETE")
        //         .allowedHeaders("Authorization", "Content-Type")
        //         .exposedHeaders("Custom-Header")
        //         .allowCredentials(true)
        //         .maxAge(3600);
    }
}