package AssetOS.model;

public class LocationStat {
    private String name;
    private int assetCount;
    private String valueFormatted;

    public LocationStat() {}

    public LocationStat(String name, int assetCount, String valueFormatted) {
        this.name = name;
        this.assetCount = assetCount;
        this.valueFormatted = valueFormatted;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAssetCount() { return assetCount; }
    public void setAssetCount(int assetCount) { this.assetCount = assetCount; }

    public String getValueFormatted() { return valueFormatted; }
    public void setValueFormatted(String valueFormatted) { this.valueFormatted = valueFormatted; }
}
