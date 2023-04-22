package io.akhutail.mail.folders.stats;

import org.springframework.data.cassandra.repository.MapIdCassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepo extends MapIdCassandraRepository<FolderStats> {
    @Query("select numRead from folder_Stats where user_id = ?0 and label = ?1")
    Integer findNumReadByUserIdAndLabel(String userId, String label);

    @Query("update folder_stats set numRead = numRead+1 where user_id = ?0 and label = ?1")
    Object incrementNumReadByUserIdAndLabel(String userId, String label);

}

