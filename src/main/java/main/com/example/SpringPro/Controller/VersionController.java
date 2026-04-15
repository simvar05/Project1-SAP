package main.com.example.SpringPro.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import main.com.example.SpringPro.Model.Document;
import main.com.example.SpringPro.Model.DocumentVersion;
import main.com.example.SpringPro.Model.User;
import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
    @ResponseBody
    public List<DocumentVersion> getAllVersions() throws RuntimeException {

        return documentService.getDocumentVersions();
    }

    @PostMapping("/versions")
    public DocumentVersion createVersion(@RequestBody CombinedDocUserDTO request) throws RuntimeException {
        return documentService.createVersion(request.getDocument_id(), request.getDocument_content(), request.getUserid());
    }

    @PostMapping("/approve")
    @ResponseBody
    public ResponseEntity<String> approveVersion(@RequestBody CombinedDocUserDTO request, HttpSession session) throws RuntimeException {
        try {
            User loggedUser=(User) session.getAttribute("user");
            documentService.approvedVersion(loggedUser.getId(), request.getDocument_id(), request.getEdit());
            return ResponseEntity.ok("Document approved successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/decline")
    @ResponseBody
    public ResponseEntity<String> declineVersion(@RequestBody CombinedDocUserDTO request, HttpSession session) throws RuntimeException {
        try {
            User loggedUser = (User) session.getAttribute("user");
            documentService.declinedVersion(loggedUser.getId(), request.getEdit(), request.getDocument_id());
            return ResponseEntity.ok("Document rejected successfully");
        }
        catch(Exception e){
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
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
@ResponseBody
    public DocumentVersion doneVersion(@RequestBody CombinedDocUserDTO request,HttpSession session) throws RuntimeException {
        User user= (User) session.getAttribute("user");
        return documentService.versionDone(user.getId(), request.getDocument_id());
}

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = documentService.loginUser(email, password);

        if (user != null) {
            if(user.getPassword().equals(password)) {
                session.setAttribute("user", user);
                return "redirect:/documents";
            }
        }

        model.addAttribute("error", "Грешен имейл или парола!");
        return "login";
    }

}


