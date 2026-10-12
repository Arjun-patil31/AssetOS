package AssetOS.model;

public class AssetDisposal {
    private String disposalNo;
    private String date;
    private String assetTag;
    private String assetName;
    private String category;
    private String method;     // Auction, Scrap, Donate, Write-off
    private String valueRecovered;
    private String disposedBy;
    private String approvedBy;
    private String remarks;
    private String department = "Operations";

    public AssetDisposal() {}

    public AssetDisposal(String disposalNo, String date, String assetTag, String assetName,
                         String category, String method, String valueRecovered,
                         String disposedBy, String approvedBy, String remarks) {
        this.disposalNo = disposalNo;
        this.date = date;
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.category = category;
        this.method = method;
        this.valueRecovered = valueRecovered;
        this.disposedBy = disposedBy;
        this.approvedBy = approvedBy;
        this.remarks = remarks;
    }

    public String getMethodBadgeClass() {
        if (method == null) return "badge-secondary";
        switch (method.trim().toLowerCase()) {
            case "auction":   return "badge-assigned";
            case "donate":    return "badge-available";
            case "scrap":     return "badge-retired";
            case "write-off": return "badge-maintenance";
            default:          return "badge-secondary";
        }
    }

    // Getters & Setters
    public String getDisposalNo() { return disposalNo; }
    public void setDisposalNo(String disposalNo) { this.disposalNo = disposalNo; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getValueRecovered() { return valueRecovered; }
    public void setValueRecovered(String valueRecovered) { this.valueRecovered = valueRecovered; }
    public String getDisposedBy() { return disposedBy; }
    public void setDisposedBy(String disposedBy) { this.disposedBy = disposedBy; }
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getDepartment() { return department != null ? department : "Operations"; }
    public void setDepartment(String department) { this.department = department; }

    public String getDisposalValue() { return valueRecovered != null ? valueRecovered : "₹0"; }
    public void setDisposalValue(String val) { this.valueRecovered = val; }
}
