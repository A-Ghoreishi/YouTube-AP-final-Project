package program.youtube;

public class Video {
    private String thumbsrc;
    private String name;
    private String channnel;

    private String profilesrc;

    public String getProfilesrc() {
        return profilesrc;
    }

    public void setProfilesrc(String profilesrc) {
        this.profilesrc = profilesrc;
    }

    private String views;
    private String date;

    public String getThumbsrc() {
        return thumbsrc;
    }

    public void setThumbsrc(String thumbsrc) {
        this.thumbsrc = thumbsrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannnel() {
        return channnel;
    }

    public void setChannnel(String channnel) {
        this.channnel = channnel;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
