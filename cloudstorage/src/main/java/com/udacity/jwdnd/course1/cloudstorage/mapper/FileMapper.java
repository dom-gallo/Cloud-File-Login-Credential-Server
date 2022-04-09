package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    // Get all files for user
    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getFilesForUserId(int userId);

    //Insert file into database for user
    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insertFile(File file);

    //DELETE
    @Delete("DELETE FROM FILES WHERE fileId=#{fileId} AND userId=#{userId}")
    int deleteFile(int fileId, int userId);

    @Select("SELECT * FROM FILES WHERE fileName=#{fileName}")
    File checkIfFileExistsForName(String fileName);

    @Select("SELECT * FROM FILES WHERE fileId=#{fileId} AND userId=#{userId}")
    File getFileForId(int fileId, int userId);
}
