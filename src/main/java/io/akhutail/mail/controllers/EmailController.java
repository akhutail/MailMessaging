package io.akhutail.mail.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.akhutail.mail.emails.emailService.emailService;
import io.akhutail.mail.emails.emailsById.EmailsById;
import io.akhutail.mail.emails.emailsById.EmailsRepo;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolder;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolderRepo;


@RestController
@CrossOrigin
public class EmailController {

    @Autowired 
    private EmailsByUserFolderRepo emailsByFolderRepo;
    @Autowired private EmailsRepo emailsRepo;
    @Autowired private emailService emailService;
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
    
    public EmailsById getEmail(@RequestParam UUID mailId){//@RequestParam(value="userId") String userId
    String userId = "akhutail";
     
        if(userId != null && mailId != null){
            EmailsById email = emailsRepo.findById(mailId);
            return email;
        }

        return null;
    }

    @PostMapping(value = "/email")

    public UUID postEmail(@RequestBody String mail) {
        UUID mailID = Uuids.timeBased();
        //System.out.println(mail);
        ObjectMapper objectMapper = new ObjectMapper();
        EmailsById email;
        try {
            email = objectMapper.readValue(mail, EmailsById.class);
            System.out.println(email);
            //email.set
            emailService.sendEmail(email);

            
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return mailID;
    }
}
