package main.com.example.SpringPro.Controller;

public class CommentDTO {
    private Long userid;
    private Long versionid;
    private String comment;

    public CommentDTO(Long userid, Long versionid, String text) {
        this.userid = userid;
        this.versionid = versionid;
        this.comment = comment;
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
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment= comment;
    }

    public CommentDTO() {}
}
