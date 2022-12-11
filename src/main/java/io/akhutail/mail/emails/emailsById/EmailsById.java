package io.akhutail.mail.emails.emailsById;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(value = "emails_by_id")
public class EmailsById {

    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TIMEUUID)
    private UUID id;

    //@PrimaryKeyColumn(name = "from", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    @CassandraType(type = CassandraType.Name.TEXT)
    private String from;

    //@JsonProperty
    @CassandraType(type = CassandraType.Name.LIST, typeArguments = Name.TEXT)
    private List<String> to;

    //@JsonProperty
    @CassandraType(type = CassandraType.Name.TEXT)
    private String subject;

    //@JsonProperty
    @CassandraType(type = CassandraType.Name.TEXT)
    private String body;

    public UUID getId() {
        return id;
    }
    public EmailsById(){}

    public EmailsById(UUID id, String from, List<String> to, String subject, String body) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
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

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Override
    public String toString() {
        return "EmailsById [id=" + id + ", from=" + from + ", to=" + to + ", subject=" + subject + ", body=" + body
                + "]";
    }
    
    
}
