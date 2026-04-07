package main.com.example.SpringPro.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
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