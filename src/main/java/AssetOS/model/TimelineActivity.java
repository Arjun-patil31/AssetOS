package AssetOS.model;

public class TimelineActivity {
    private String type;        // "ASSET", "ASSIGNMENT", "RETURN", "MAINTENANCE"
    private String icon;        // "📦", "👤", "🔄", "🛠️"
    private String iconClass;   // "icon-asset", "icon-assignment", "icon-return", "icon-maintenance"
    private String title;       // e.g. "Asset Assigned to Karim Uddin"
    private String subtitle;    // e.g. "Assignment #ASN0019 • By Admin"
    private String date;        // e.g. "09 Aug 2026"

    public TimelineActivity() {}

    public TimelineActivity(String type, String icon, String iconClass, String title, String subtitle, String date) {
        this.type = type;
        this.icon = icon;
        this.iconClass = iconClass;
        this.title = title;
        this.subtitle = subtitle;
        this.date = date;
    }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }

    public String getIconClass() { return iconClass; }
    public void setIconClass(String iconClass) { this.iconClass = iconClass; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
