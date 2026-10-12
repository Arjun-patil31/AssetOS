package AssetOS.model;

public class MaintenanceAlert {
    private String assetName;
    private String type;         // Corrective | Preventive
    private String status;       // Open | In Progress
    private String alertBadge;   // Due Soon | In Progress

    public MaintenanceAlert() {}

    public MaintenanceAlert(String assetName, String type, String status, String alertBadge) {
        this.assetName = assetName;
        this.type = type;
        this.status = status;
        this.alertBadge = alertBadge;
    }

    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAlertBadge() { return alertBadge; }
    public void setAlertBadge(String alertBadge) { this.alertBadge = alertBadge; }
}
