//package cn.edu.jnu.devhub.config;
//
//import cn.edu.jnu.devhub.interceptor.AdminCheckInterceptor;
//import cn.edu.jnu.devhub.interceptor.UserCheckInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private AdminCheckInterceptor adminCheckInterceptor;
//
//    @Autowired
//    private UserCheckInterceptor userCheckInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //定义拦截对象
//        registry.addInterceptor(adminCheckInterceptor);
//    }
//}
