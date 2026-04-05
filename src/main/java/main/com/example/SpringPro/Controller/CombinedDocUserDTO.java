package main.com.example.SpringPro.Controller;

import main.com.example.SpringPro.Model.User;

public class CombinedDocUserDTO {

    private Long document_id;
    private String document_content;
    private Long userid;
    private String edit;

    public Long getUserid() {
        return userid;
    }
    public void setUserid(Long userid) {
        this.userid = userid;
    }
    public String getDocument_content() {
        return document_content;
    }
    public void setDocument_content(String document_content) {
        this.document_content = document_content;
    }
    public Long getDocument_id() {
        return document_id;
    }
    public void setDocument_id(Long document_id) {
        this.document_id = document_id;
    }
    public String getEdit() {
        return edit;
    }
    public void setEdit(String edit) {
            this.edit = edit;
    }

}
