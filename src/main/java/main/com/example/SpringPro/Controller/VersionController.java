package main.com.example.SpringPro.Controller;

import main.com.example.SpringPro.Model.DocumentVersion;
import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VersionController {

    DocumentService documentService;



    public VersionController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/versions/allVersions")
    public List<DocumentVersion> getAllVersions() throws RuntimeException {

        return documentService.getDocumentVersions();
    }

    @PostMapping("/versions/create")
    public DocumentVersion createVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {
        return documentService.createVersion(request.getDocument_id(), request.getDocument_content(), request.getUserid());
    }

    @PostMapping("/versions/approve/{id}")
    public String approveVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {


        System.out.println("DEBUG: Получено ID от Postman: " + request.getUserid());
        System.out.println("DEBUG: Получен Edit от Postman: " + request.getEdit());
        documentService.approvedVersion(request.getUserid(),request.getDocument_id(), request.getEdit());
        return "New Version approved for document: " + request.getDocument_id();

    }

    @PostMapping("/versions/decline/{id}")
    public String declineVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {
        documentService.declinedVersion(request.getUserid(), request.getEdit(),request.getDocument_id());
        return "New Version Declined for document: " + request.getDocument_id();
    }

}
