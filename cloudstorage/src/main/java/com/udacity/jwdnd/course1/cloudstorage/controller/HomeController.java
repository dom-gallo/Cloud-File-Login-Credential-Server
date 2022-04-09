package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final NoteService noteService;
    private final CredentialService credentialService;
    private final EncryptionService encryptionService;
    private final FileService fileService;

    public HomeController(UserService userService, NoteService noteService, CredentialService credentialService,
                          EncryptionService encryptionService, FileService fileService) {
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.fileService = fileService;
    }
    
    
    @GetMapping
    public String getHomeView(Authentication auth, Note note, Credential credential, File file, Model model){
        
        System.out.println("Home controller hit");
        
        // get current username
       String currentUsername = auth.getName();
       
       //  get userID from DB
       int userId = userService.getUser(currentUsername).getUserId();
        // get notes state from DB
       List<Note> notes = noteService.getNotes(userId);
        // get credentials from DB
        List<Credential> credentials = credentialService.getCredentialsForUserId(userId);
        // get files from DB
        List<File> files = fileService.getFilesForUserId(userId);

        model.addAttribute("files", files);
        model.addAttribute("notes", notes);
        model.addAttribute("CredentialList", credentials);
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }
}
