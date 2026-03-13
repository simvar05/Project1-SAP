package Model;

import Repo.Status_documentVer;
import jdk.jshell.Snippet;
import org.hibernate.engine.spi.Status;

import java.time.LocalTime;

public class DocumentVersion {

    private Long id;
    private Document document;
    private int documentVersion;
    private LocalTime checkedAT;
    Status_documentVer status;
    private String name;
    private String edit;


    public DocumentVersion(String edit,String name, Long id, Document document, LocalTime checkedAT, Status_documentVer status) {

        this.name = name;
            this.id = id;
            this.document = document;
            this.checkedAT = checkedAT;
            this.status = status;
            this.documentVersion = 1;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    public void setDocumentVersion(int documentVersion) {
        this.documentVersion = documentVersion;
    }
    public void setStatus(Status_documentVer status) {
        this.status = status;
    }
    public void setCheckAT(LocalTime checkedAT) {
        this.checkedAT = checkedAT;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Status_documentVer getStatus() {
        return status;
    }
    public int getDocumentVersion() {
        return documentVersion;
    }
public LocalTime getDate() {
    return checkedAT;
}
    public Long getId(){
        return id;
    }
    public Document getDocument() {
        return document;
    }
    public String getEdit() {
        return edit;


    }
    public String toString() {
        return "ID: " + id + " Name: " + name + " Document: " + document + " Check AT: " + checkedAT + " Status: " + status;
    }







}
