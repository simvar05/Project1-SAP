package main.com.example.SpringPro.constants;

import main.com.example.SpringPro.Repo.CommentsRepo;
import main.com.example.SpringPro.Repo.DocumentRepository;
import main.com.example.SpringPro.Repo.UserRepo;
import main.com.example.SpringPro.Repo.VersionRepo;

public class Constants {

    private final DocumentRepository documentRepo;
    private final VersionRepo versionRepo;
    private final UserRepo userRepo;
    private final CommentsRepo commentsRepo;

    public Constants(DocumentRepository documentRepo, VersionRepo versionRepo, UserRepo userRepo, CommentsRepo commentsRepo) {
        this.documentRepo = documentRepo;
        this.versionRepo = versionRepo;
        this.userRepo = userRepo;
        this.commentsRepo = commentsRepo;
    }


}
