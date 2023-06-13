package io.akhutail.mail.emails.emailService;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.internal.core.cql.DefaultBatchStatement;
import io.akhutail.mail.controllers.requestbody.DeleteMailRequestBody;
import io.akhutail.mail.emails.emailsById.EmailsById;
import io.akhutail.mail.emails.emailsById.EmailsRepo;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolder;
import io.akhutail.mail.emails.emailsByUserFolder.EmailsByUserFolderRepo;
import io.akhutail.mail.folders.FolderService;
import io.akhutail.mail.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class  emailService {
    @Autowired private EmailsRepo emailsRepo;

    @Autowired private EmailsByUserFolderRepo emailsByUserFolderRepo;
    
    @Autowired private FolderService folderService;
    @Autowired private CqlSession cqlSession;
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

    public boolean deleteMails(DeleteMailRequestBody deleteReq){
        List<UUID> mailIds = new ArrayList<>(deleteReq.getMailIds());
        String folder = deleteReq.getFolderName();

        /* ideally delete from these tables in a logged batch, but even then isolation is a problem
        currently this system is not consistent w.r.t the basic requirement of emails_by_id, emails_by_user_folder
        and email_stats, just writing a logged batch for now ignoring isolation and performance impact TODO */

        // since counter updates can't be with non counter updates
        int readMailsGettingDeleted = emailsByUserFolderRepo.getNumMailsRead(CommonUtils.getAuthenticatedEmail(), folder, mailIds);

        try {
            folderService.updateStats(folder, readMailsGettingDeleted);
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }
        BatchStatementBuilder batchBuilder = BatchStatement.builder(BatchType.LOGGED);

        for(UUID mailId : mailIds){
           /*  don't delete from emails_by_id as needed for receivers, so need to check them TODO
               batchBuilder.addStatement(QueryBuilder.deleteFrom("main", "emails_by_id")
                    .whereColumn("id").isEqualTo(QueryBuilder.literal(mailId)).build()); */
            batchBuilder.addStatement(QueryBuilder.deleteFrom("main", "emails_by_user_folder")
                    .whereColumn("user_id").isEqualTo(QueryBuilder.literal(CommonUtils.getAuthenticatedEmail()))
                    .whereColumn("label").isEqualTo(QueryBuilder.literal(folder))
                    .whereColumn("mail_id").isEqualTo(QueryBuilder.literal(mailId))
                    .build());
        }

        try{
            cqlSession.execute(batchBuilder.build());
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }

        return true;
    }
}
