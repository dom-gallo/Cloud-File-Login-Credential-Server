package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }
    public List<Note> getNotes(int userid){
        return noteMapper.getNotes(userid);
    }
    public int createNote(Note note){
        return noteMapper.createNote(note);
    }
    public int updateNote(Note note){
        return noteMapper.updateNote(note);
    }
    public int deleteNote(int noteId, int userId){
        return noteMapper.deleteNote(noteId, userId);
    }
}
