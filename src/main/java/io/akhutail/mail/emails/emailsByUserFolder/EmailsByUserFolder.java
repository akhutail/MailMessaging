package io.akhutail.mail.emails.emailsByUserFolder;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value = "emails_by_user_folder")
public class EmailsByUserFolder {
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String userId;

    @PrimaryKeyColumn(name = "label", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String label;

    @PrimaryKeyColumn(name = "mail_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID id;

    @CassandraType(type = Name.TEXT)
    private String from;

    @CassandraType(type = Name.TEXT)
    private String subject;

    @CassandraType(type = Name.TEXT)
    private String bodyBrief;

    @CassandraType(type = Name.BOOLEAN)
    private Boolean isRead;




    public EmailsByUserFolder(String userId, String label, UUID id, String from, String subject, String bodyBrief, Boolean isRead) {
        this.userId = userId;
        this.label = label;
        this.id = id;
        this.from = from;
        this.subject = subject;
        this.bodyBrief = bodyBrief;
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
    public String getPrefix() {
        return bodyBrief;
    }

    public void setPrefix(String prefix) {
        this.bodyBrief = prefix;
    }

    
}
