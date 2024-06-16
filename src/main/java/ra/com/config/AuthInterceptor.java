package ra.com.config;

import org.springframework.web.servlet.HandlerInterceptor;
import ra.com.modules.users.Users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Users user = (Users) request.getSession().getAttribute("user");
        if (user == null){
            response.sendRedirect("/login");
            return false;
        }
        if(user.getUserRole()){
            return true;
        } else {
            response.sendRedirect("/");
            return false;
        }
    }
}