package AssetOS.model;

public class MaintenanceRecord {
    private String ticketId;
    private String assetTag;
    private String assetName;
    private String category;
    private String issueDescription;
    private String technicianName;
    private String priority;         // Critical | High | Medium | Low
    private String status;           // Open | In Progress | Pending Parts | Resolved | Closed
    private String reportedDate;
    private String estimatedCompletion;
    private String resolvedDate;     // blank if not resolved
    private String department = "IT";

    public MaintenanceRecord() {}

    public MaintenanceRecord(String ticketId, String assetTag, String assetName, String category,
                             String issueDescription, String technicianName, String priority,
                             String status, String reportedDate,
                             String estimatedCompletion, String resolvedDate) {
        this.ticketId = ticketId;
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.category = category;
        this.issueDescription = issueDescription;
        this.technicianName = technicianName;
        this.priority = priority;
        this.status = status;
        this.reportedDate = reportedDate;
        this.estimatedCompletion = estimatedCompletion;
        this.resolvedDate = resolvedDate;
    }

    public String getStatusBadgeClass() {
        if (status == null) return "badge-secondary";
        switch (status.trim().toLowerCase()) {
            case "open":           return "badge-retired";
            case "in progress":    return "badge-maintenance";
            case "pending parts":  return "badge-maintenance";
            case "resolved":       return "badge-assigned";
            case "closed":         return "badge-available";
            default:               return "badge-secondary";
        }
    }

    public String getPriorityBadgeClass() {
        if (priority == null) return "badge-secondary";
        switch (priority.trim().toLowerCase()) {
            case "critical": return "badge-retired";
            case "high":     return "badge-maintenance";
            case "medium":   return "badge-assigned";
            case "low":      return "badge-available";
            default:         return "badge-secondary";
        }
    }

    // Getters & Setters
    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getIssueDescription() { return issueDescription; }
    public void setIssueDescription(String issueDescription) { this.issueDescription = issueDescription; }

    public String getTechnicianName() { return technicianName; }
    public void setTechnicianName(String technicianName) { this.technicianName = technicianName; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReportedDate() { return reportedDate; }
    public void setReportedDate(String reportedDate) { this.reportedDate = reportedDate; }

    public String getEstimatedCompletion() { return estimatedCompletion; }
    public void setEstimatedCompletion(String estimatedCompletion) { this.estimatedCompletion = estimatedCompletion; }

    public String getResolvedDate() { return resolvedDate; }
    public void setResolvedDate(String resolvedDate) { this.resolvedDate = resolvedDate; }

    public String getDepartment() { return department != null ? department : "IT"; }
    public void setDepartment(String department) { this.department = department; }

    public String getVendorTechnician() { return technicianName != null ? technicianName : "Facility Maintenance"; }
}
