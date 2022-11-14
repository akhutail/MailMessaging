package io.akhutail.mail.emails.emailsByUserFolder;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import lombok.Data;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
@Data
@Table(value = "emails_by_user_folder")
public class EmailsByUserFolder {
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;

    @PrimaryKeyColumn(name = "label", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String label;

    @PrimaryKeyColumn(name = "id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID id;
    
    @CassandraType(type = Name.TEXT)
    private String from;

    @CassandraType(type = Name.TEXT)
    private String subject;

    @CassandraType(type = Name.BOOLEAN)
    private Boolean isRead;

    public EmailsByUserFolder(String userId, String label, UUID id, String from, String subject, Boolean isRead) {
        this.userId = userId;
        this.label = label;
        this.id = id;
        this.from = from;
        this.subject = subject;
        this.isRead = isRead;
    }

}
