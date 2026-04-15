package main.com.example.SpringPro.Controller;

import main.com.example.SpringPro.Model.Document;
import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    DocumentService documentService;

    public PageController(DocumentService documentService) {
    this.documentService = documentService;
}

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {


            List<Document> allDocs = documentService.getDocuments();

            model.addAttribute("documents", allDocs);
            model.addAttribute("documentsCount", allDocs.size());
            model.addAttribute("pendingCount", allDocs.stream().filter(doc->"STILL".equalsIgnoreCase(doc.getStatus())).count());
            model.addAttribute("approvedCount", allDocs.stream().filter(doc->"APPROVED".equalsIgnoreCase(doc.getStatus())).count());

            return "dashboard";
        }

    @GetMapping("/documents")
    public String documents(Model model) {
        return "documents";
    }

    @GetMapping("/documents/new")
    public String newDocumentPage(Model model) {
        return "create-document";
    }

    @GetMapping("/documents/{id}/view")
    public String documentDetails() {
        return "document-details";
    }
}