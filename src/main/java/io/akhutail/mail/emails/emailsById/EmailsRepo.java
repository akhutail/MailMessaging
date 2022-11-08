package io.akhutail.mail.emails.emailsById;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface EmailsRepo extends CassandraRepository<EmailsById, String>{
    List<EmailsById> findAllById(UUID id);
}