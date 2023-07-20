package cn.edu.jnu.devhub.interceptor;

import cn.edu.jnu.devhub.result.ResultCode;
import cn.edu.jnu.devhub.result.ResultFactory;
import cn.edu.jnu.devhub.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCheck {
    public static boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1,先获取请求头
        String token = request.getHeader("token");
        response.setContentType("application/json;charset = UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        //2,判断请求头是否存在
        if (token == null || "".equals(token)) {
            //请求头不存在或者请求头为空
            String result = mapper.writeValueAsString(ResultFactory.buildResult(ResultCode.FAILURE, "please login", null));
            response.getWriter().write(result);
            return false;
        }
        //3,请求头不正确
        return JwtUtils.checkJwt(token) && !JwtUtils.isExpire(token);
    }
}
