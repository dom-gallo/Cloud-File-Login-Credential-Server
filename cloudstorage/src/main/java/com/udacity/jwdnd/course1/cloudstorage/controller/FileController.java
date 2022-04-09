package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/file")
public class FileController {

    FileService fileService;
    UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/upload")
    public String createFile(@RequestParam MultipartFile fileUpload, Authentication auth, RedirectAttributes redirectAttributes) throws Exception
    {
        System.out.println("FILE UPLOAD CONTROLLER HIT");
        int userId = userService.getUser(auth.getName()).getUserId();
        if (fileService.fileDoesExistWithThatName(fileUpload.getOriginalFilename())){
            redirectAttributes.addAttribute("message", "file already exists with that filename");
            redirectAttributes.addAttribute("activatedTab", "files");
            return "redirect:/home";
//            throw new Exception("File already exists with that filename");
        }
        System.out.println("fileName OK");
        int fileId =  fileService.insertFile(fileUpload,userId);
        if (fileId < 0 ) {
            throw new Exception("Error inserting file into database");
        }
        System.out.println("redirecting to home");
        return "redirect:/home";
    }
    @GetMapping("/download/{fileId}")
    public ResponseEntity downloadFileForId(@PathVariable int fileId, Authentication auth) throws Exception{
        int userId = userService.getUser(auth.getName()).getUserId();
        File file = fileService.getFileForId(fileId, userId);
        if(file == null){
            return ResponseEntity.badRequest().body("User may not have permission to view file or it does not exist");
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getFileData());
    }
    @GetMapping("/delete/{fileId}")
    public String deleteFileForId(@PathVariable int fileId, Authentication auth) {
        int userId = userService.getUser(auth.getName()).getUserId();
        int rowsAffected =  fileService.deleteFileWithId(fileId, userId);

        return "redirect:/home";
    }
}
