package AssetOS.service;

import AssetOS.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        // Pre-configure standard default accounts matching the reference UI
        users.put("admin", new User("admin", "admin123", "System Administrator (Admin)", "Administrator", "admin@assetos.local", "AD"));
        users.put("manager", new User("manager", "manager123", "Asset Manager", "Manager", "manager@assetos.local", "AM"));
        users.put("itstaff", new User("itstaff", "staff123", "Ravi Mehra", "IT Support", "r.mehra@assetos.local", "RM"));
    }

    public User authenticate(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        User user = users.get(username.trim().toLowerCase());
        if (user != null && user.getPassword().equals(password.trim())) {
            return user;
        }
        return null;
    }

    public User findByUsername(String username) {
        if (username == null) return null;
        return users.get(username.trim().toLowerCase());
    }
}
