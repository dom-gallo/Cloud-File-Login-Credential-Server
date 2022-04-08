package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
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

    public HomeController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping
    public String getHomeView(Authentication auth, Note note, Model model){

       String currentUsername = auth.getName();
       int userId = userService.getUser(currentUsername).getUserId();

       List<Note> notes = noteService.getNotes(userId);

        model.addAttribute("notes", notes);

        System.out.println("Home controller hit");
        return "home";
    }
}
