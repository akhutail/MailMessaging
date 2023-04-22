package io.akhutail.mail.emails.emailsByUserFolder;

import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;
import java.util.UUID;


public interface EmailsByUserFolderRepo extends MapIdCassandraRepository<EmailsByUserFolder>{
    //List<EmailsByUserFolder> findAllById(String id);
    List<EmailsByUserFolder> findAllByUserIdAndLabel(String id, String label);

    @Query("update emails_by_user_folder set isread = true where user_id = ?0 and label = ?1 and mail_id = ?2")
    Object setReadForUserFolderMail(String authenticatedEmail, String folder, UUID mailId);

    @Query("select isread from emails_by_user_folder where user_id = ?0 and label = ?1 and mail_id = ?2")
    boolean getIsReadByUserFolderEmail(String userId, String folder, UUID mailId);
}