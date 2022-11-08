package io.akhutail.mail.emails;

import java.util.List;

import org.springframework.data.cassandra.repository.MapIdCassandraRepository;


public interface EmailsByUserFolderRepo extends MapIdCassandraRepository<EmailsByUserFolder>{
    //List<EmailsByUserFolder> findAllById(String id);
    List<EmailsByUserFolder> findAllByUserIdAndLabel(String id, String label);
}