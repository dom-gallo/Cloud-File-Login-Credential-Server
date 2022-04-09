package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int insertFile(MultipartFile file, int userId) throws IOException {

        File fileToUpload = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(), userId, file.getBytes());
        return fileMapper.insertFile(fileToUpload);
    }

    public List<File> getFilesForUserId(int userId){
        return fileMapper.getFilesForUserId(userId);
    }

    public int deleteFiles(int fileId, int userId){
        return fileMapper.deleteFile(fileId, userId);
    }

    public boolean fileDoesExistWithThatName(String fileName){
        File fileOptional = fileMapper.checkIfFileExistsForName(fileName);
        if (fileOptional != null){
            return true;
        }
        return false;
    }
    public File getFileForId(int fileId, int userId){
        return fileMapper.getFileForId(fileId, userId);
    }

    public int deleteFileWithId(int fileId, int userId){
        return fileMapper.deleteFile(fileId, userId);
    }
}
