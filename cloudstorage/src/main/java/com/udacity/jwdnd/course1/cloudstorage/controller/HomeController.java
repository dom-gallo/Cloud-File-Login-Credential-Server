package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    UserService userService;
    NoteService noteService;
    CredentialService credentialService;

    public HomeController(UserService userService, NoteService noteService, CredentialService credentialService) {
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String getHomeView(Authentication auth, Note note, Credential credential, Model model){

       String currentUsername = auth.getName();
       int userId = userService.getUser(currentUsername).getUserId();

       List<Note> notes = noteService.getNotes(userId);
        List<Credential> credentials = List.of(
                new Credential(1,"github.com", "dewclaw",
                        "encryptionkey",
                        "encryptedPassword", 1),
                new Credential(1,"facebook.com", "dgallo1122",
                        "encryptionkey",
                        "encryptedPassword", 1)
        );
        model.addAttribute("notes", notes);
        model.addAttribute("credentialList", credentials);
        System.out.println("Home controller hit");
        return "home";
    }
}
