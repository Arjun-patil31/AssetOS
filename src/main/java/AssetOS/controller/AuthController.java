package AssetOS.controller;

import AssetOS.model.User;
import AssetOS.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            HttpSession session,
                            Model model) {
        if (session != null && session.getAttribute("currentUser") != null) {
            return "redirect:/";
        }

        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password. Please try again.");
        }
        if (logout != null) {
            model.addAttribute("successMessage", "You have been successfully logged out.");
        }

        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpServletRequest request,
                              Model model) {
        User user = authService.authenticate(username, password);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("currentUser", user);
            return "redirect:/";
        }

        model.addAttribute("errorMessage", "Invalid username or password. Use demo account: admin / admin123");
        model.addAttribute("username", username);
        return "login";
    }

    @GetMapping("/logout")
    public String handleLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login?logout=true";
    }
}
