package main.com.example.SpringPro.Controller;


import main.com.example.SpringPro.Model.Comment;
import main.com.example.SpringPro.Model.User;
import main.com.example.SpringPro.Service.DocumentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    DocumentService documentService;

    public UserController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/users/userCreate")
    public User createUser(@RequestBody User user) throws RuntimeException {
        return documentService.createUser(user.getId(),user.getRoles(),user.getUsername(), user.getPassword());
    }
    @GetMapping("users/userCreate/{id}")
    public User getUser(@PathVariable Long id, Model model) throws RuntimeException {
        model.addAttribute("users",documentService.getUserById(id));
        return documentService.getUserById(id);
    }

    @PostMapping("/users/comment/{id}")
    public Comment createComment(@PathVariable Long id,@RequestBody CommentDTO comment) throws RuntimeException {
        return documentService.addComment(id,comment.getUserid(),comment.getText(),comment.getVersionid());
    }

}
