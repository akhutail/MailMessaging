package io.akhutail.mail.folders;

import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends MapIdCassandraRepository<FolderByUser> {
    List<FolderByUser> findAllById(String id);
}
