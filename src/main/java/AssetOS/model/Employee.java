package AssetOS.model;

public class Employee {
    private String employeeId;
    private String fullName;
    private String email;
    private String phone;
    private String mobile;
    private String department;
    private String jobTitle;
    private String branch;
    private String location;
    private String status;       // Active | Inactive
    private String joinDate;
    private int assignedAssetCount;

    public Employee() {}

    public Employee(String employeeId, String fullName, String email, String phone,
                    String department, String jobTitle, String location,
                    String status, String joinDate, int assignedAssetCount) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.mobile = phone;
        this.department = department;
        this.jobTitle = jobTitle;
        this.branch = location;
        this.location = location;
        this.status = status;
        this.joinDate = joinDate;
        this.assignedAssetCount = assignedAssetCount;
    }

    public Employee(String employeeId, String fullName, String email, String mobile,
                    String department, String jobTitle, String branch,
                    String status) {
        this.employeeId = employeeId;
        this.fullName = fullName;
        this.email = email;
        this.phone = mobile;
        this.mobile = mobile;
        this.department = department;
        this.jobTitle = jobTitle;
        this.branch = branch;
        this.location = branch;
        this.status = status;
        this.joinDate = "10 Aug 2026";
        this.assignedAssetCount = 1;
    }

    public String getStatusBadgeClass() {
        if (status == null) return "badge-secondary";
        switch (status.trim().toLowerCase()) {
            case "active":   return "badge-available";
            case "on leave": return "badge-maintenance";
            case "inactive": return "badge-retired";
            default:         return "badge-secondary";
        }
    }

    // Getters & Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getJoinDate() { return joinDate; }
    public void setJoinDate(String joinDate) { this.joinDate = joinDate; }

    public int getAssignedAssetCount() { return assignedAssetCount; }
    public void setAssignedAssetCount(int assignedAssetCount) { this.assignedAssetCount = assignedAssetCount; }

    public String getMobile() { return mobile != null ? mobile : phone; }
    public void setMobile(String mobile) { this.mobile = mobile; this.phone = mobile; }

    public String getBranch() { return branch != null ? branch : location; }
    public void setBranch(String branch) { this.branch = branch; this.location = branch; }

    // Initials for avatar display
    public String getInitials() {
        if (fullName == null || fullName.isBlank()) return "?";
        String[] parts = fullName.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Math.min(2, parts.length); i++) {
            sb.append(Character.toUpperCase(parts[i].charAt(0)));
        }
        return sb.toString();
    }
}
