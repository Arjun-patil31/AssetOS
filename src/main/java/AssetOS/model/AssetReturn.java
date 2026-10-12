package AssetOS.model;

public class AssetReturn {
    private String returnNo;
    private String date;
    private String assetTag;
    private String assetName;
    private String category;
    private String returnedBy;
    private String employeeId;
    private String department;
    private String condition;
    private String damageStatus = "No Damage";
    private String remarks;
    private String processedBy;

    public AssetReturn() {}

    public AssetReturn(String returnNo, String date, String assetTag, String assetName,
                       String category, String returnedBy, String employeeId,
                       String department, String condition, String remarks, String processedBy) {
        this(returnNo, date, assetTag, assetName, category, returnedBy, employeeId, department, condition, "No Damage", remarks, processedBy);
    }

    public AssetReturn(String returnNo, String date, String assetTag, String assetName,
                       String category, String returnedBy, String employeeId,
                       String department, String condition, String damageStatus, String remarks, String processedBy) {
        this.returnNo = returnNo;
        this.date = date;
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.category = category;
        this.returnedBy = returnedBy;
        this.employeeId = employeeId;
        this.department = department;
        this.condition = condition;
        this.damageStatus = (damageStatus != null && !damageStatus.isBlank()) ? damageStatus : "No Damage";
        this.remarks = remarks;
        this.processedBy = processedBy;
    }

    public String getConditionBadgeClass() {
        if (condition == null) return "badge-secondary";
        switch (condition.trim().toLowerCase()) {
            case "new":  return "badge-available";
            case "good": return "badge-assigned";
            case "fair": return "badge-maintenance";
            case "poor": return "badge-retired";
            default:     return "badge-secondary";
        }
    }

    // Getters & Setters
    public String getReturnNo() { return returnNo; }
    public void setReturnNo(String returnNo) { this.returnNo = returnNo; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getReturnedBy() { return returnedBy; }
    public void setReturnedBy(String returnedBy) { this.returnedBy = returnedBy; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public String getDamageStatus() { return damageStatus != null ? damageStatus : "No Damage"; }
    public void setDamageStatus(String damageStatus) { this.damageStatus = damageStatus; }
    public String getProcessedBy() { return processedBy; }
    public void setProcessedBy(String processedBy) { this.processedBy = processedBy; }
}
