package AssetOS.model;

public class DepreciationRecord {
    private String assetId;
    private String assetName;
    private String purchaseDate;
    private String purchaseCost;
    private String method;           // SLM | WDV | Not Applicable
    private String usefulLifeRate;   // e.g. "5 Yrs", "12%", "—"
    private String startDate;
    private String accumulatedDep;
    private String currentBookValue;
    private String status;
    private String department;
    private String category;

    public DepreciationRecord() {}

    public DepreciationRecord(String assetId, String assetName, String purchaseDate,
                              String purchaseCost, String method, String usefulLifeRate,
                              String startDate, String accumulatedDep, String currentBookValue,
                              String status) {
        this(assetId, assetName, purchaseDate, purchaseCost, method, usefulLifeRate, startDate, accumulatedDep, currentBookValue, status, "General", "General");
    }

    public DepreciationRecord(String assetId, String assetName, String purchaseDate,
                              String purchaseCost, String method, String usefulLifeRate,
                              String startDate, String accumulatedDep, String currentBookValue,
                              String status, String department, String category) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.purchaseDate = purchaseDate;
        this.purchaseCost = purchaseCost;
        this.method = method;
        this.usefulLifeRate = usefulLifeRate;
        this.startDate = startDate;
        this.accumulatedDep = accumulatedDep;
        this.currentBookValue = currentBookValue;
        this.status = status;
        this.department = department;
        this.category = category;
    }

    public String getStatusBadgeClass() {
        if (status == null) return "badge-secondary";
        switch (status.trim().toLowerCase()) {
            case "available": return "badge-available";
            case "assigned": return "badge-assigned";
            case "under maintenance": return "badge-maintenance";
            case "retired": return "badge-retired";
            default: return "badge-secondary";
        }
    }

    public String getAssetId() { return assetId; }
    public void setAssetId(String assetId) { this.assetId = assetId; }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getPurchaseCost() { return purchaseCost; }
    public void setPurchaseCost(String purchaseCost) { this.purchaseCost = purchaseCost; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getUsefulLifeRate() { return usefulLifeRate; }
    public void setUsefulLifeRate(String usefulLifeRate) { this.usefulLifeRate = usefulLifeRate; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getAccumulatedDep() { return accumulatedDep; }
    public void setAccumulatedDep(String accumulatedDep) { this.accumulatedDep = accumulatedDep; }

    public String getCurrentBookValue() { return currentBookValue; }
    public void setCurrentBookValue(String currentBookValue) { this.currentBookValue = currentBookValue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDepartment() { return department != null ? department : "General"; }
    public void setDepartment(String department) { this.department = department; }

    public String getCategory() { return category != null ? category : "General"; }
    public void setCategory(String category) { this.category = category; }
}
