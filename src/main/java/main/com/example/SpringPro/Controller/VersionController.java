package main.com.example.SpringPro.Controller;

import jakarta.servlet.http.HttpSession;
import main.com.example.SpringPro.Model.Document;
import main.com.example.SpringPro.Model.DocumentVersion;
import main.com.example.SpringPro.Model.User;
import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class VersionController {

    DocumentService documentService;



    public VersionController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/versions/allVersions")
    public List<DocumentVersion> getAllVersions() throws RuntimeException {

        return documentService.getDocumentVersions();
    }

    @PostMapping("/versions")
    public DocumentVersion createVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {
        return documentService.createVersion(request.getDocument_id(), request.getDocument_content(), request.getUserid());
    }

    @PostMapping("/approve")
    public String approveVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {


        System.out.println("DEBUG: Получено ID от Postman: " + request.getUserid());
        System.out.println("DEBUG: Получен Edit от Postman: " + request.getEdit());
        documentService.approvedVersion(request.getUserid(),request.getDocument_id(), request.getEdit());
        return "New Version approved for document: " + request.getDocument_id();

    }

    @PostMapping("/decline")
    public String declineVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {
        documentService.declinedVersion(request.getUserid(), request.getEdit(),request.getDocument_id());
        return "New Version Declined for document: " + request.getDocument_id();
    }

    @PostMapping("/versionModify")
    public DocumentVersion modifyDocument( @RequestBody CombinedDocUserDTO request) throws RuntimeException {
       return documentService.editDocument(request.getUserid(), request.getDocument_id());

    }
@GetMapping("/versions/{user_id}")
    public List<DocumentVersion> getAllActiveVersions(@PathVariable Long user_id) throws RuntimeException {

        return documentService.seeAllActiveVersions(user_id);
}
@GetMapping("/versions/{user_id}/{div}")
    public List<DocumentVersion> getHistory(@PathVariable Long user_id, @PathVariable Long div) throws RuntimeException {

        return documentService.historyOfDocumentVersion(user_id, div);
}
@PostMapping("/versions/done")
    public DocumentVersion doneVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {
        return documentService.versionDone(request.getUserid(), request.getDocument_id());
}

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = documentService.loginUser(email, password);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                return "redirect:/documents";
            }
        }

        model.addAttribute("error", "Грешен имейл или парола!");
        return "login";
    }

}


