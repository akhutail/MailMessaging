package io.akhutail.mail.folders.stats;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "folder_stats")
public class FolderStats {
    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id;

    @PrimaryKeyColumn(name = "label", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String label;

    @CassandraType(type = CassandraType.Name.COUNTER)

    private int numRead;

    public FolderStats(String id, String label, int numRead) {
        this.id = id;
        this.label = label;
        this.numRead = numRead;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public FolderStats() {
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNumRead() {
        return numRead;
    }

    public void setNumRead(int numRead) {
        this.numRead = numRead;
    }
}
