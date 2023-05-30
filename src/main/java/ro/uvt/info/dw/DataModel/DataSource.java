package ro.uvt.info.dw.DataModel;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Set;

@Table("dataSource")
public class DataSource {

    @PrimaryKey
    private String name;
    private String description;
    private Timestamp system_date;
    private Set<String> attributes;

    public DataSource() {
    }

    public DataSource(int id, String name, String description, Timestamp system_date, Set<String> attributes) {
        this.name = name;
        this.description = description;
        this.system_date = system_date;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getSystem_date() {
        return system_date;
    }

    public void setSystem_date(Timestamp system_date) {
        this.system_date = system_date;
    }

    public Set<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<String> attributes) {
        this.attributes = attributes;
    }
}
