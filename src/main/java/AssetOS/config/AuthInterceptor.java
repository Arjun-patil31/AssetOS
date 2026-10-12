package AssetOS.config;

import AssetOS.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        // Allow public static resources and auth endpoints
        if (path.startsWith("/login") || path.startsWith("/logout") ||
            path.startsWith("/css/") || path.startsWith("/js/") ||
            path.startsWith("/images/") || path.equals("/favicon.ico")) {
            return true;
        }

        HttpSession session = request.getSession(false);
        User currentUser = (session != null) ? (User) session.getAttribute("currentUser") : null;

        if (currentUser == null) {
            response.sendRedirect("/login");
            return false;
        }

        // Make currentUser available in the request scope so all templates can access it
        request.setAttribute("currentUser", currentUser);
        return true;
    }
}
