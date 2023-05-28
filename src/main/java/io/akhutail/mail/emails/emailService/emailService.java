package io.akhutail.mail.emails.emailService;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.akhutail.mail.emails.emailsById.EmailsById;
import io.akhutail.mail.emails.emailsById.EmailsRepo;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolder;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolderRepo;
import io.akhutail.mail.folders.FolderService;
import io.akhutail.mail.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;


@Service
public class  emailService {
    @Autowired private EmailsRepo emailsRepo;

    @Autowired private EmailsByUserFolderRepo emailsByUserFolderRepo;
    
    @Autowired private FolderService folderService;
    public UUID sendEmail(EmailsById email) {

        UUID timeUuid = Uuids.timeBased();
        List<String> toUserIdList = email.getTo().stream()
        		.filter(StringUtils::hasText)
        		.map(String::strip)
        		.toList();
       
        String senderEmail = email.getFrom();
        EmailsByUserFolder sentItemEntry = new EmailsByUserFolder(senderEmail, "Sent", timeUuid, senderEmail,
                                                                  email.getSubject(), CommonUtils.getBodyBrief(email.getBody()), false);

        // adding to Sent Folder of sender
        
        emailsByUserFolderRepo.save(sentItemEntry);
        
        
        EmailsByUserFolder inboxEntry = sentItemEntry;
        inboxEntry.setIsRead(false);
        inboxEntry.setLabel("Inbox");

        // Adding to inbox of each reciever
        toUserIdList.forEach(toUserId -> {
            inboxEntry.setUserId(toUserId);
            emailsByUserFolderRepo.save(inboxEntry);
        });

        // Save email
        email.setId(timeUuid);
        email.setFrom(senderEmail);
        emailsRepo.save(email);

        return timeUuid;
    }


    public EmailsById setReadAndGetMail(UUID mailId, String folder) {
        if (!emailsByUserFolderRepo.getIsReadByUserFolderEmail(CommonUtils.getAuthenticatedEmail(), folder, mailId)){
            folderService.updateStats(folder);
            System.out.println(emailsByUserFolderRepo.setReadForUserFolderMail(CommonUtils.getAuthenticatedEmail(), folder, mailId));
        }

        return emailsRepo.findById(mailId);
    }
}
