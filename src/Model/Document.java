package Model;

import jakarta.persistence.*;



@Entity
public class Document {

    @Id
    @GeneratedValue
    private Long id;
    private String documentContent;
    private String name;

    public Document(Long id, String documentContent, String name) {
        this.id = id;
        this.documentContent = documentContent;
        this.name=name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDocumentContent() {
        return documentContent;
    }
    public void setDocumentContent(String documentContent) {
        this.documentContent = documentContent;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


}
