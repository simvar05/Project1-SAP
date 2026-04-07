package main.com.example.SpringPro.Controller;

public class CommentDTO {
    private Long userid;
    private Long versionid;
    private String text;

    public CommentDTO(Long userid, Long versionid, String text) {
        this.userid = userid;
        this.versionid = versionid;
        this.text = text;
    }
    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public Long getVersionid() {
        return versionid;
    }
    public void setVersionid(Long versionid) {
        this.versionid = versionid;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

}
