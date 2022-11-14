package io.akhutail.mail.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.akhutail.mail.emails.emailsById.EmailsById;
import io.akhutail.mail.emails.emailsById.EmailsRepo;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolder;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolderRepo;


@RestController
@CrossOrigin
public class EmailController {

    @Autowired private EmailsByUserFolderRepo emailsByFolderRepo;
    @Autowired private EmailsRepo emailsRepo;
    
    @GetMapping(value = "/emailsByFolder")
    
    public List<EmailsByUserFolder> getEmailsByFolder(@RequestParam String folderLabel){//@RequestParam(value="userId") String userId
    String userId = "akhutail";
     
        if(userId != null && folderLabel != null){
            List<EmailsByUserFolder>  emails = emailsByFolderRepo.findAllByUserIdAndLabel(userId, folderLabel);
            return emails;
        }

        return null;
    }

    @GetMapping(value = "/email")
    
    public List<EmailsById> getEmail(@RequestParam UUID mailId){//@RequestParam(value="userId") String userId
    String userId = "akhutail";
     
        if(userId != null && mailId != null){
            List<EmailsById>  emails = emailsRepo.findAllById(mailId);
            return emails;
        }

        return null;
    }
}
