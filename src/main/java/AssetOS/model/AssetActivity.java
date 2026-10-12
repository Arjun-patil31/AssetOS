package AssetOS.model;

public class AssetActivity {
    private String assetTag;
    private String assetName;
    private String category;
    private String assignedTo;
    private String department;
    private String status;
    private String date;

    public AssetActivity() {
    }

    public AssetActivity(String assetTag, String assetName, String category, String assignedTo, String department, String status, String date) {
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.category = category;
        this.assignedTo = assignedTo;
        this.department = department;
        this.status = status;
        this.date = date;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatusBadgeClass() {
        if (status == null) {
            return "badge-secondary";
        }
        switch (status.trim().toLowerCase()) {
            case "available":
                return "badge-available";
            case "assigned":
                return "badge-assigned";
            case "under maintenance":
            case "in maintenance":
                return "badge-maintenance";
            case "retired":
                return "badge-retired";
            default:
                return "badge-secondary";
        }
    }
}
