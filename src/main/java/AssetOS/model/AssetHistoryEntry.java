package AssetOS.model;

public class AssetHistoryEntry {
    private String date;
    private String activity;
    private String details;
    private String actor;

    public AssetHistoryEntry() {}

    public AssetHistoryEntry(String date, String activity, String details, String actor) {
        this.date = date;
        this.activity = activity;
        this.details = details;
        this.actor = actor;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getActivity() { return activity; }
    public void setActivity(String activity) { this.activity = activity; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }

    public String getActor() { return actor; }
    public void setActor(String actor) { this.actor = actor; }
}
