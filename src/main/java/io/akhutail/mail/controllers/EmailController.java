package io.akhutail.mail.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.akhutail.mail.emails.EmailsByUserFolder;
import io.akhutail.mail.emails.EmailsByUserFolderRepo;


@RestController
@CrossOrigin
public class EmailController {

    @Autowired private EmailsByUserFolderRepo emailsByFolderRepo;

    
    @GetMapping(value = "/emailsByFolder")
    
    public List<EmailsByUserFolder> getEmailsByFolder(@RequestParam String folderLabel){//@RequestParam(value="userId") String userId
    String userId = "akhutail";
     
        if(userId != null && folderLabel != null){
            List<EmailsByUserFolder>  emails = emailsByFolderRepo.findAllByUserIdAndLabel(userId, folderLabel);
            return emails;
        }

        return null;
    }
}
