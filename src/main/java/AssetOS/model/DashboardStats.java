package AssetOS.model;

public class DashboardStats {
    private long totalAssets;
    private long availableAssets;
    private long assignedAssets;
    private long underMaintenance;
    private long disposedAssets;
    private String totalPurchaseValue;
    private String currentBookValue;
    private long warrantyExpiringSoon;
    private double utilizationRate;

    public DashboardStats() {
    }

    public DashboardStats(long totalAssets, long availableAssets, long assignedAssets,
                          long underMaintenance, long disposedAssets,
                          String totalPurchaseValue, String currentBookValue,
                          long warrantyExpiringSoon) {
        this.totalAssets = totalAssets;
        this.availableAssets = availableAssets;
        this.assignedAssets = assignedAssets;
        this.underMaintenance = underMaintenance;
        this.disposedAssets = disposedAssets;
        this.totalPurchaseValue = totalPurchaseValue;
        this.currentBookValue = currentBookValue;
        this.warrantyExpiringSoon = warrantyExpiringSoon;
        this.utilizationRate = totalAssets > 0 ? ((double) assignedAssets / totalAssets) * 100.0 : 0.0;
    }

    public long getTotalAssets() { return totalAssets; }
    public void setTotalAssets(long totalAssets) { this.totalAssets = totalAssets; }

    public long getAvailableAssets() { return availableAssets; }
    public void setAvailableAssets(long availableAssets) { this.availableAssets = availableAssets; }

    public long getAssignedAssets() { return assignedAssets; }
    public void setAssignedAssets(long assignedAssets) { this.assignedAssets = assignedAssets; }

    public long getUnderMaintenance() { return underMaintenance; }
    public void setUnderMaintenance(long underMaintenance) { this.underMaintenance = underMaintenance; }

    public long getDisposedAssets() { return disposedAssets; }
    public void setDisposedAssets(long disposedAssets) { this.disposedAssets = disposedAssets; }

    public String getTotalPurchaseValue() { return totalPurchaseValue; }
    public void setTotalPurchaseValue(String totalPurchaseValue) { this.totalPurchaseValue = totalPurchaseValue; }

    public String getCurrentBookValue() { return currentBookValue; }
    public void setCurrentBookValue(String currentBookValue) { this.currentBookValue = currentBookValue; }

    public long getWarrantyExpiringSoon() { return warrantyExpiringSoon; }
    public void setWarrantyExpiringSoon(long warrantyExpiringSoon) { this.warrantyExpiringSoon = warrantyExpiringSoon; }

    public double getUtilizationRate() { return utilizationRate; }
    public void setUtilizationRate(double utilizationRate) { this.utilizationRate = utilizationRate; }
}
