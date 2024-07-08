package program.youtube;

public class UserInfo {
    private String email;
    private String password;
    private String bio;

    private int year;
    private int day;
    private String month;
    private String gender;
    private String username;
    private String name;
    private String lname;

    // Constructor
    public UserInfo() {
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public String getUsername() {
        return username;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getLname() {
        return lname;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
