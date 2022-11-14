package io.akhutail.mail.emails.emailsById;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.MapIdCassandraRepository;

public interface EmailsRepo extends MapIdCassandraRepository<EmailsById>{
    List<EmailsById> findAllById(UUID id);
}