package AssetOS.model;

public class AssetTransferRecord {
    private String transferNo;
    private String date;
    private String assetTag;
    private String assetName;
    private String category;
    private String fromDepartment;
    private String toDepartment;
    private String fromEmployee;
    private String toEmployee;
    private String reason;
    private String transferredBy;
    private String remarks;

    public AssetTransferRecord() {}

    public AssetTransferRecord(String transferNo, String date, String assetTag, String assetName,
                               String category, String fromDepartment, String toDepartment,
                               String fromEmployee, String toEmployee, String reason,
                               String transferredBy, String remarks) {
        this.transferNo = transferNo;
        this.date = date;
        this.assetTag = assetTag;
        this.assetName = assetName;
        this.category = category;
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.fromEmployee = fromEmployee;
        this.toEmployee = toEmployee;
        this.reason = reason;
        this.transferredBy = transferredBy;
        this.remarks = remarks;
    }

    // Getters & Setters
    public String getTransferNo() { return transferNo; }
    public void setTransferNo(String transferNo) { this.transferNo = transferNo; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getFromDepartment() { return fromDepartment; }
    public void setFromDepartment(String fromDepartment) { this.fromDepartment = fromDepartment; }
    public String getToDepartment() { return toDepartment; }
    public void setToDepartment(String toDepartment) { this.toDepartment = toDepartment; }
    public String getFromEmployee() { return fromEmployee; }
    public void setFromEmployee(String fromEmployee) { this.fromEmployee = fromEmployee; }
    public String getToEmployee() { return toEmployee; }
    public void setToEmployee(String toEmployee) { this.toEmployee = toEmployee; }
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public String getTransferredBy() { return transferredBy; }
    public void setTransferredBy(String transferredBy) { this.transferredBy = transferredBy; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
