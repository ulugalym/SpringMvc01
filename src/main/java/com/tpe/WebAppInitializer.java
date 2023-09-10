package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Java tabanli web uyg. web.xml dosyasi ile config edilir.
//web.xml yerine bu classi kullanacagiz.

//AbstractAnnotationCDSI classinin methodlarini override ederek DispatcherServlet'in config
// ve baslatilmasini kolaylastirir.
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /*
    dispatcher:
            Servlet WebAppContext-->controllers-viewResolver-handlerMapping
            Root WebAppContext-->dataya erisim: services-repos
     */

    @Override
    protected Class<?>[] getRootConfigClasses() {//dataya erisim(hibernate)
        return new Class[]{
                RootContextConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {//controllers-viewResolver=handlerMapping
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }
}
