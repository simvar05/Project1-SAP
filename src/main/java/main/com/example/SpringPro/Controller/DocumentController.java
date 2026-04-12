package main.com.example.SpringPro.Controller;

import jakarta.servlet.http.HttpSession;
import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.com.example.SpringPro.Model.User;
import main.com.example.SpringPro.Model.DocumentVersion;
import main.com.example.SpringPro.Model.Document;
import main.com.example.SpringPro.Repo.*;

import java.util.List;


@Controller
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;

    }


    @PostMapping("/documents")
    public String createDocument(@RequestBody DocumentDTO docDto, HttpSession session) {

        User loggedUser = (User) session.getAttribute("user");

        if (loggedUser == null) {
            return "redirect:/login";
        }


            Document newDoc = documentService.createDocument(
                    loggedUser.getId(),
                    docDto.getDocument_content(),
                    docDto.getName()
            );

        return "redirect:/dashboard";

    }


}
