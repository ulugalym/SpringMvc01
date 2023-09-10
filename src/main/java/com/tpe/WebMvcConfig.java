package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe")
@EnableWebMvc //MVC config etkinlestirme
public class WebMvcConfig implements WebMvcConfigurer {

    //view resolver icin
    @Bean
    public InternalResourceViewResolver resolver(){

        InternalResourceViewResolver resolver=new InternalResourceViewResolver();

        resolver.setViewClass(JstlView.class); //JavaStandardTagLibrary:JSP icine daha az kod yazmamizi saglar
        resolver.setPrefix("/WEB-INF/views/");//view dosyalari nerede
        resolver.setSuffix(".jsp"); //view name'in uzantisini belirliyoruz

        return resolver;

    }

    //static kaynaklarin dispatcher'e gonderilmesine gerek yok


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").//bu pathdeki kaynaklari statik olarak sun
                addResourceLocations("/resources").//kaynaklarinyeri
                setCachePeriod(0);//cache'leme icin sure verilebilir
    }
}
