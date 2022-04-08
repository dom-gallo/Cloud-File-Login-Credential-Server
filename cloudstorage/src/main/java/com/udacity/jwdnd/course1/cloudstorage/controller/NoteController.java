package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/{userid}")
    public List<Note> getNotes(@RequestParam int userid){
        System.out.println("NOTES CONTROLLER");
        System.out.println(userid);
        return noteService.getNotes(userid);
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable int noteId, Authentication auth) throws Exception{
        System.out.println("delete note controller");
        System.out.println("delete note with ID : " + noteId);
        int userId = userService.getUser(auth.getName()).getUserId();
        int rowsAffected = noteService.deleteNote(noteId, userId);

        if (rowsAffected < 0 ){
            throw new Exception("unable to delete note from db");
        }

        return "redirect:/home";
    }
    @PostMapping()
    public String createNote(Authentication auth, Note note) throws Exception{
        int userId = userService.getUser(auth.getName()).getUserId();
        note.setUserId(userId);

        //This is a note that needs to be created.
        if (note.getNoteId() == null){
            int noteId = noteService.createNote(note);
            if (noteId < 0) {
                throw new Exception("Unable to enter new note in database");
            }

            System.out.println(note.toString());

            return "redirect:/home";
        }
        // This is a note that needs to be updated

        int numRowsAffected = noteService.updateNote(note);
        if (numRowsAffected < 0) {
            throw new Exception("Error updating note in database, try again");
        }

        return "redirect:/home";
    }


}
