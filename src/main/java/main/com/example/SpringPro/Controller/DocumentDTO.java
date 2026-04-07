package main.com.example.SpringPro.Controller;

import main.com.example.SpringPro.Model.User;

public class DocumentDTO {

    private Long id;
    private Long doc_id;
    private String document_content;
    private String name;
    public DocumentDTO(Long id, Long doc_id, String content, String name) {

        this.id = id;
        this.doc_id=doc_id;
        this.document_content = content;
        this.name = name;

    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setDocument_content(String document_content) {
        this.document_content = document_content;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public String getDocument_content() {
        return document_content;
    }
    public String getName() {
        return name;
    }

   public   void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
    }
    public Long getDoc_id() {
        return doc_id;
    }

}

