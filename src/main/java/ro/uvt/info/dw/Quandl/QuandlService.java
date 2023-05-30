package ro.uvt.info.dw.Quandl;

import com.jimmoores.quandl.*;
import com.jimmoores.quandl.classic.ClassicQuandlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.uvt.info.dw.DataModel.Asset;
import ro.uvt.info.dw.DataModel.Data;
import ro.uvt.info.dw.MongoDataModel.AssetMongo;
import ro.uvt.info.dw.Repository.AssetRepository;
import ro.uvt.info.dw.Repository.DataRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Service
public class QuandlService {

    private final String QUANDL_DATA_SOURCE_ID = "Quandl";

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    DataRepository dataRepository;

//    @Autowired
//    AssetMongoRepository assetMongoRepository;

    public void transformAndSaveAsset(String stockLabel){
        TabularResult assetTabular = extractAssetQuandlData(stockLabel);

        // Save the Asset where the data was extracted from
        Asset a = new Asset();
        Date sys_date = new Date();
        a.setSystem_date(new Timestamp(sys_date.getTime()));
        a.setName(stockLabel);
        a.setDescription("Stock from " + stockLabel + " taken at: " + new Timestamp(sys_date.getTime()).toString());
        assetRepository.save(a);

        transformAndSaveDataForAssets(assetTabular, a);
    }

    private TabularResult extractAssetQuandlData(String stockLabel) {
        ClassicQuandlSession assetSession = ClassicQuandlSession.create();
        TabularResult tabularResult = assetSession.getDataSet(DataSetRequest.Builder.of(stockLabel).build());
        return tabularResult;
    }

    public void transformAndSaveDataForAssets(TabularResult assetTabular, Asset asset){
        List<Data> dataExtractedList = new ArrayList<>();

        // Iterate over each fetched row in order to save the data from the asset
        for(final Row row : assetTabular) {

            Map<String, Double> valuesDouble = new HashMap<>();
            Map<String, Integer> valuesInteger = new HashMap<>();
            Map<String, String> valuesString = new HashMap<>();

            final Data data = new Data();

            data.setAssetId(asset.getName());
            Date sys_date = new Date();
            data.setSystemDate(new Timestamp(sys_date.getTime()));
            data.setDataSourceId(QUANDL_DATA_SOURCE_ID);

            for(final String col : assetTabular.getHeaderDefinition().getColumnNames()) {
                if (col.equals("Date")) {
                    LocalDate localDate = row.getLocalDate(col);
                    Timestamp ts = Timestamp.valueOf(localDate.atStartOfDay());
                    data.setBusinessDate(ts);
                }
                if (col.equals("High")) {
                    Double high = row.getDouble(col);
                    valuesDouble.put(col, high);
                }
                if (col.equals("Low")) {
                    Double low = row.getDouble(col);
                    valuesDouble.put(col, low);
                }
                if (col.equals("Close")) {
                    Double close = row.getDouble(col);
                    valuesDouble.put(col, close);
                }
                if (col.equals("Volume")) {
                    Double volume = row.getDouble(col);
                    valuesDouble.put(col, volume);
                }
                if (col.equals("Ex-Dividend")) {
                    Double exDivident = row.getDouble(col);
                    valuesInteger.put(col, exDivident.intValue());
                }
                if (col.equals("Split Ratio")) {
                    Double splitRatio = row.getDouble(col);
                    valuesString.put(col, splitRatio.toString());
                }
            }

            data.setValuesDouble(valuesDouble);
            data.setValuesInt(valuesInteger);
            data.setValuesText(valuesString);

            dataExtractedList.add(data);
            dataRepository.saveAll(dataExtractedList);
        }
    }
}
