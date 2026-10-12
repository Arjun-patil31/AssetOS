package AssetOS.model;

public class Assignment {
    private String assignmentId;
    private String assetTag;
    private String assetName;
    private String category;
    private String employeeName;
    private String employeeId;
    private String department;
    private String assignedDate;
    private String expectedReturn;   // blank = permanent
    private String status;           // Active | Pending Return | Returned | Overdue

    public Assignment() {}

    public Assignment(String assignmentId, String assetTag, String assetName, String category,
                      String employeeName, String employeeId, String department,
                      String assignedDate, String expectedReturn, String status) {
        this.assignmentId = assignmentId;
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.category = category;
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.department = department;
        this.assignedDate = assignedDate;
        this.expectedReturn = expectedReturn;
        this.status = status;
    }

    public String getStatusBadgeClass() {
        if (status == null) return "badge-secondary";
        switch (status.trim().toLowerCase()) {
            case "active":          return "badge-assigned";
            case "pending return":  return "badge-maintenance";
            case "returned":        return "badge-available";
            case "overdue":         return "badge-retired";
            default:                return "badge-secondary";
        }
    }

    // Getters & Setters
    public String getAssignmentId() { return assignmentId; }
    public void setAssignmentId(String assignmentId) { this.assignmentId = assignmentId; }

    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getAssignedDate() { return assignedDate; }
    public void setAssignedDate(String assignedDate) { this.assignedDate = assignedDate; }

    public String getExpectedReturn() { return expectedReturn; }
    public void setExpectedReturn(String expectedReturn) { this.expectedReturn = expectedReturn; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAssignedBy() { return "Admin"; }
    public String getAssignmentDate() { return assignedDate; }
}
