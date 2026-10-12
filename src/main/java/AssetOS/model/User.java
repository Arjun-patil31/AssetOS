package AssetOS.model;

public class User {
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String email;
    private String avatarInitials;

    public User() {}

    public User(String username, String password, String fullName, String role, String email, String avatarInitials) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.email = email;
        this.avatarInitials = avatarInitials;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarInitials() {
        if (avatarInitials != null && !avatarInitials.isBlank()) return avatarInitials;
        if (fullName != null && !fullName.isBlank()) {
            String[] parts = fullName.trim().split("\\s+");
            if (parts.length >= 2) {
                return ("" + parts[0].charAt(0) + parts[1].charAt(0)).toUpperCase();
            }
            return fullName.substring(0, Math.min(2, fullName.length())).toUpperCase();
        }
        return username != null ? username.substring(0, Math.min(2, username.length())).toUpperCase() : "US";
    }

    public void setAvatarInitials(String avatarInitials) {
        this.avatarInitials = avatarInitials;
    }

    public String getRoleBadgeClass() {
        if (role == null) return "badge-secondary";
        switch (role.toLowerCase()) {
            case "administrator": return "badge-retired";
            case "asset manager": return "badge-assigned";
            case "it support": return "badge-maintenance";
            default: return "badge-available";
        }
    }
}
