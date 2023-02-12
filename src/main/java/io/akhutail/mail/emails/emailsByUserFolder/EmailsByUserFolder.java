package io.akhutail.mail.emails.emailsByUserFolder;

import java.util.UUID;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "emails_by_user_folder")
public class EmailsByUserFolder {
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;

    @PrimaryKeyColumn(name = "label", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String label;

    @PrimaryKeyColumn(name = "id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID id;
    
    @PrimaryKeyColumn(name = "from", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = Name.TEXT)
    private String from;

    @CassandraType(type = Name.TEXT)
    private String subject;

    @PrimaryKeyColumn(name = "is_read", ordinal = 4, type = PrimaryKeyType.CLUSTERED)
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }
    
}
