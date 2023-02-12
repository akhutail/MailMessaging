package io.akhutail.mail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.akhutail.mail.folders.Folder;
import io.akhutail.mail.folders.FolderRepository;
import io.akhutail.mail.folders.FolderService;


@RestController

public class FolderController {

    @Autowired private FolderRepository folderRepository;

    
    @GetMapping(value = "/folders")
    
    public List<Folder> getHomePage(){//@RequestParam(value="userId") String userId
        
        
        String userId = "akhutail";
        List<Folder> userFolders = folderRepository.findAllById(userId);

        return userFolders;
    }
}
