package shop.mtcoding.aopstudy.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import shop.mtcoding.aopstudy.model.User;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        HttpSession session = request.getSession();
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().println("잘못된 접근이라네요");
            return false;
        } else {
            return true; // 다시 그 메서드에 진입해라// 근데 그 세션값을 자동으로 주입시켜주고 싶다.
        }
    }
}
