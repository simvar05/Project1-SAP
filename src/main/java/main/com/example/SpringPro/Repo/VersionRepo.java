package main.com.example.SpringPro.Repo;

import main.com.example.SpringPro.Model.DocumentVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionRepo extends JpaRepository<DocumentVersion,Long> {
    List<DocumentVersion> findByDocumentId(Long id);

    List<DocumentVersion> findByName(String name);
    DocumentVersion findByDocumentVersion(int documentVersion);
    List<DocumentVersion> findByStatus(Status_documentVer status);
    DocumentVersion findTopByOrderByIdDesc();
}