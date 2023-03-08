package io.akhutail.mail.controllers;

import io.akhutail.mail.folders.FolderByUser;
import io.akhutail.mail.folders.FolderService;
import io.akhutail.mail.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class FolderController {

    @Autowired private FolderService folderService;

    
    @GetMapping(value = "/folders")
    public List<FolderByUser> getFolders(){
        String userId = CommonUtils.getAuthenticatedEmail();

        return folderService.getFolders(userId);
    }
}
