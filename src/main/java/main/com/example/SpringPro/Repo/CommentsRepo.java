package main.com.example.SpringPro.Repo;

import main.com.example.SpringPro.Model.Comment;
import main.com.example.SpringPro.Model.Document;
import main.com.example.SpringPro.Model.DocumentVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepo extends JpaRepository<Comment, Long> {
     Comment findTopByOrderByIdDesc();
    List<Comment> findByVersion_IdAndVersion_Document_Id(Long versionId, Long documentId);
    List<Comment> findByVersion(DocumentVersion documentVersion);
    List<Comment> findByDocument(Document document);
    List<Comment> findByDocumentId(Long documentId);
}
