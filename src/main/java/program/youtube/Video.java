package program.youtube;

public class Video {
    private String thumbsrc;
    private String name;
    private String channnel;

    private String profilesrc;

    private String views;
    private String date;

    public void setProfilesrc(String profilesrc) {
        this.profilesrc = profilesrc;
    }

    public String getProfilesrc() {
        return profilesrc;
    }

    public void setThumbsrc(String thumbsrc) {
        this.thumbsrc = thumbsrc;
    }


    public String getThumbsrc() {
        return thumbsrc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setChannel(String channnel) {
        this.channnel = channnel;
    }

    public String getChannel() {
        return channnel;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getViews() {
        return views;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
