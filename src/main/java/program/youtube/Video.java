package program.youtube;

public class Video {
    private String thumbsrc;
    private String name;
    private String channel;
    private String profilesrc;
    private String views;
    private String date;
    private String videosrc; // Field to store video source URL or path

    private String description;

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getProfilesrc() {
        return profilesrc;
    }

    public void setProfilesrc(String profilesrc) {
        this.profilesrc = profilesrc;
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

    public String getVideosrc() {
        return videosrc;
    }

    public void setVideosrc(String videosrc) {
        this.videosrc = videosrc;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String descriptionText) {
        this.description = description;
    }
}
