package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
    List<Note> getNotes(int userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNote(Note note);

    @Update("UPDATE NOTES SET notetitle=#{noteDescription}, notedescription=#{noteDescription} WHERE noteId=#{noteId}")
//    @Update("UPDATE NOTES SET (notetitle, notedescription) VALUES(#{noteTitle}, #{noteDescription} WHERE noteId = #{noteId}")
    int updateNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid=#{noteId} AND userid=#{userId}")
    int deleteNote(int noteId, int userId);
}
