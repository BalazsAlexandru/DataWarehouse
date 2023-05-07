package ro.uvt.info.dw.DataModel;


import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@Table("data")
public class Data {

    @PrimaryKey
    private int assetId;
    private int dataSourceId;
    private Date businessDate;
    private Timestamp systemDate;
    private Map<String, Double> valuesDouble;
    private Map<String, Integer> valuesInt;
    private Map<String, String> valuesText;

    public Data() {
    }

    public Data(int assetId, int dataSourceId, Date businessDate, Timestamp systemDate, Map<String, Double> valuesDouble, Map<String, Integer> valuesInt, Map<String, String> valuesText) {
        this.assetId = assetId;
        this.dataSourceId = dataSourceId;
        this.businessDate = businessDate;
        this.systemDate = systemDate;
        this.valuesDouble = valuesDouble;
        this.valuesInt = valuesInt;
        this.valuesText = valuesText;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setAssetId(int assetId) {
        this.assetId = assetId;
    }

    public int getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(int dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public Timestamp getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Timestamp systemDate) {
        this.systemDate = systemDate;
    }

    public Map<String, Double> getValuesDouble() {
        return valuesDouble;
    }

    public void setValuesDouble(Map<String, Double> valuesDouble) {
        this.valuesDouble = valuesDouble;
    }

    public Map<String, Integer> getValuesInt() {
        return valuesInt;
    }

    public void setValuesInt(Map<String, Integer> valuesInt) {
        this.valuesInt = valuesInt;
    }

    public Map<String, String> getValuesText() {
        return valuesText;
    }

    public void setValuesText(Map<String, String> valuesText) {
        this.valuesText = valuesText;
    }
}
