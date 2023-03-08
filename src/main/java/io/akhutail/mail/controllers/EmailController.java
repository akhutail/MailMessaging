package io.akhutail.mail.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.akhutail.mail.emails.emailService.emailService;
import io.akhutail.mail.emails.emailsById.EmailsById;
import io.akhutail.mail.emails.emailsById.EmailsRepo;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolder;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolderRepo;
import io.akhutail.mail.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EmailController {

    @Autowired 
    private EmailsByUserFolderRepo emailsByFolderRepo;
    @Autowired private EmailsRepo emailsRepo;
    @Autowired private emailService emailService;
    
    @GetMapping(value = "/emailsByFolder")
    public List<EmailsByUserFolder> getEmailsByFolder(@RequestParam String folderLabel){//@RequestParam(value="userId") String userId
        String userId = CommonUtils.getAuthenticatedEmail();
        if(userId != null && folderLabel != null){
            return emailsByFolderRepo.findAllByUserIdAndLabel(userId, folderLabel);
        }

        return null;
    }

    @GetMapping(value = "/email")
    
    public EmailsById getEmail(@RequestParam UUID mailId){
        if(mailId != null){
            return emailsRepo.findById(mailId);
        }
        return null;
    }

    @PostMapping(value = "/email")

    public UUID postEmail(@RequestBody String mail) {
        UUID mailID = null;
        //System.out.println(mail);
        ObjectMapper objectMapper = new ObjectMapper();
        EmailsById email;
        try {
            email = objectMapper.readValue(mail, EmailsById.class);
            email.setFrom(CommonUtils.getAuthenticatedEmail());
            System.out.println(email);
            mailID = emailService.sendEmail(email);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return mailID;
    }
}
