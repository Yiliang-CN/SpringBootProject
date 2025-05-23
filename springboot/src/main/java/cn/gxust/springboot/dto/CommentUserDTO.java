package cn.gxust.springboot.dto;

public class CommentUserDTO {
    private String userName;
    private double score;
    private String time;
    private String content;
    private String image;

    public CommentUserDTO(String userName, double score, String time, String content, String image) {
        this.userName = userName;
        this.score = score;
        this.time = time;
        this.content = content;
        this.image = image;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
