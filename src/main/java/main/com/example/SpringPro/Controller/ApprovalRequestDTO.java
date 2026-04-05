package main.com.example.SpringPro.Controller;

public class ApprovalRequestDTO {
    private Long userid;
    private String edit;

    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
            this.userid = userid;
    }
    public String getEdit() {
        return edit;
    }
    public void setEdit(String edit) {
        this.edit = edit;
    }
    public ApprovalRequestDTO() {}
}
