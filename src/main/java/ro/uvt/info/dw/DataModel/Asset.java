package ro.uvt.info.dw.DataModel;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Map;

@Table("asset")
public class Asset {

    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private Timestamp systemDate;
    private Map<String, String> attributes;

    public Asset(int id, String name, String description, Timestamp systemDate,
                 Map<String, String> attributes) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.systemDate = systemDate;
        this.attributes = attributes;
    }

    public Asset() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return systemDate;
    }

    public void setSystem_date(Timestamp system_date) {
        this.systemDate = system_date;
    }

    public Map<String, String> getAdditional_attributes() {
        return attributes;
    }

    public void setAdditional_attributes(Map<String, String> additional_attributes) {
        this.attributes = additional_attributes;
    }

}
