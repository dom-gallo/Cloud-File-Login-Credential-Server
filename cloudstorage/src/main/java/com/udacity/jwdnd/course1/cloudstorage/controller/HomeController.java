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
        
        System.out.println("Home controller hit");
        
        // get current username
       String currentUsername = auth.getName();
       
       // get userID from DB
       int userId = userService.getUser(currentUsername).getUserId();
       
        // get initial notes state from DB
       List<Note> notes = noteService.getNotes(userId);
       
       List<Credential> credentials = credentialService.getMockCredentials();
       
       for (Credential cred: credentials){
           System.out.println(cred.toString());
       }
       
       model.addAttribute("notes", notes);
       
       
       model.addAttribute("CredentialList", credentials);
       
       
       
       return "home";
    }
}
