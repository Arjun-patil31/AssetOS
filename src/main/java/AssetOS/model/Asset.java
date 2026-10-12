package AssetOS.model;

public class Asset {
    private String assetTag;
    private String name;
    private String category;
    private String brand;
    private String model;
    private String serialNumber;
    private String status;          // Available | Assigned | Under Maintenance | Retired
    private String assignedTo;
    private String department;
    private String purchaseDate;
    private String warrantyExpiry;
    private String location;
    private String condition;        // New | Good | Fair | Poor
    private String purchaseCost;     // e.g. "₹2,10,000"
    private String bookValue;        // e.g. "₹1,39,349"
    private String holder;           // e.g. "Fatima Begum" or "Unassigned"
    private String imageUrl;         // SVG illustration or image path

    public Asset() {}

    public Asset(String assetTag, String name, String category, String brand, String model,
                 String serialNumber, String status, String assignedTo, String department,
                 String purchaseDate, String warrantyExpiry, String location, String condition) {
        this.assetTag = assetTag;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.status = status;
        this.assignedTo = assignedTo;
        this.department = department;
        this.purchaseDate = purchaseDate;
        this.warrantyExpiry = warrantyExpiry;
        this.location = location;
        this.condition = condition;
        this.holder = assignedTo != null && !assignedTo.isBlank() ? assignedTo : "Unassigned";
    }

    public Asset(String assetTag, String name, String category, String brand, String model,
                 String serialNumber, String status, String assignedTo, String department,
                 String purchaseDate, String warrantyExpiry, String location, String condition,
                 String purchaseCost, String bookValue, String holder, String imageUrl) {
        this(assetTag, name, category, brand, model, serialNumber, status, assignedTo, department, purchaseDate, warrantyExpiry, location, condition);
        this.purchaseCost = purchaseCost;
        this.bookValue = bookValue;
        this.holder = holder;
        this.imageUrl = imageUrl;
    }

    // Derived helper for status badge CSS class
    public String getStatusBadgeClass() {
        if (status == null) return "badge-secondary";
        switch (status.trim().toLowerCase()) {
            case "available":         return "badge-available";
            case "assigned":          return "badge-assigned";
            case "under maintenance":
            case "in maintenance":    return "badge-maintenance";
            case "retired":           return "badge-retired";
            default:                  return "badge-secondary";
        }
    }

    // Derived helper for condition badge CSS class
    public String getConditionBadgeClass() {
        if (condition == null) return "badge-secondary";
        switch (condition.trim().toLowerCase()) {
            case "new":
            case "excellent": return "badge-excellent";
            case "good":      return "badge-good";
            case "fair":      return "badge-fair";
            case "poor":
            case "damaged":   return "badge-poor";
            default:          return "badge-secondary";
        }
    }

    // Getters & Setters
    public String getAssetTag() { return assetTag; }
    public void setAssetTag(String assetTag) { this.assetTag = assetTag; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
        this.holder = (assignedTo != null && !assignedTo.isBlank()) ? assignedTo : "Unassigned";
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }

    public String getWarrantyExpiry() { return warrantyExpiry; }
    public void setWarrantyExpiry(String warrantyExpiry) { this.warrantyExpiry = warrantyExpiry; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getPurchaseCost() { return purchaseCost; }
    public void setPurchaseCost(String purchaseCost) { this.purchaseCost = purchaseCost; }

    public String getBookValue() { return bookValue; }
    public void setBookValue(String bookValue) { this.bookValue = bookValue; }

    public String getHolder() { return holder != null ? holder : assignedTo; }
    public void setHolder(String holder) { this.holder = holder; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
