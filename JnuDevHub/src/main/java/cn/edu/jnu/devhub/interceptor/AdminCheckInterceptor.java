package cn.edu.jnu.devhub.interceptor;

import cn.edu.jnu.devhub.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1,先获取请求头
        String token = request.getHeader("token");
        response.setContentType("application/json;charset = UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        if (!LoginCheck.preHandle(request, response, handler)) return false;
        List<String> tokenInfo = JwtUtils.parseJwt(token);
        return tokenInfo.contains("Admin");
    }

    //目标资源方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle AdminCheck ");
    }

    //视图渲染完毕后执行，最后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion AdminCheck ");
    }
}
