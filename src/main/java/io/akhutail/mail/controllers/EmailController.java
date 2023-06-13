package io.akhutail.mail.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.akhutail.mail.controllers.requestbody.DeleteMailRequestBody;
import io.akhutail.mail.emails.emailService.emailService;
import io.akhutail.mail.emails.emailsById.EmailsById;
import io.akhutail.mail.emails.emailsById.EmailsRepo;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolderRepo;
import io.akhutail.mail.folders.FolderService;
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
    @Autowired private FolderService folderService;
    @GetMapping(value = "/emailsByFolder")
    public EmailsByFolderResponse getEmailsByFolder(@RequestParam String folderLabel){//@RequestParam(value="userId") String userId
        String userId = CommonUtils.getAuthenticatedEmail();
        if(userId != null && folderLabel != null){
            EmailsByFolderResponse response = new EmailsByFolderResponse(emailsByFolderRepo.findAllByUserIdAndLabel(userId, folderLabel),
                    folderService.getNumRead(userId, folderLabel));
            return response;
        }

        return null;
    }

    @GetMapping(value = "/email")
    
    public EmailsById getEmail(@RequestParam UUID mailId, @RequestParam String folder){
        return emailService.setReadAndGetMail(mailId, folder);
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

    @DeleteMapping(value = "/email")

    public void deleteEmail(@RequestBody DeleteMailRequestBody request) {
        System.out.println(request);
        emailService.deleteMails(request);
    }
}
