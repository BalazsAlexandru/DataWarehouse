package ro.uvt.info.dw.MongoDataModel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;
import java.util.Map;

@Document
public class AssetMongo {
    @Id
    private String name;
    private String description;
    private Timestamp systemDate;
    private Map<String, String> attributes;

    public AssetMongo() {
    }

    public AssetMongo(String name, String description, Timestamp systemDate, Map<String, String> attributes) {
        this.name = name;
        this.description = description;
        this.systemDate = systemDate;
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

    public Timestamp getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Timestamp systemDate) {
        this.systemDate = systemDate;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
