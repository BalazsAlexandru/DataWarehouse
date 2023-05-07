package ro.uvt.info.dw.Quandl;

import com.jimmoores.quandl.*;
import com.jimmoores.quandl.classic.ClassicQuandlSession;
import org.springframework.stereotype.Service;
import ro.uvt.info.dw.DataModel.Asset;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuandlService {

    private TabularResult extractAssetQuandlData() {
        ClassicQuandlSession assetSession = ClassicQuandlSession.create();
        TabularResult tabularResult = assetSession.getDataSet(DataSetRequest.Builder.of("WIKI/AAPL").build());
        return tabularResult;
    }

    public List<Asset> transformAssetData(){
        TabularResult assetTabular = extractAssetQuandlData();
        List<Asset> assetListTransformed = new ArrayList<Asset>();
        int incremented_id = 0;

        for(final Row row : assetTabular) {
            Asset a = new Asset();
            a.setId(incremented_id++);
            Date sys_date = new Date();
            a.setSystem_date(new Timestamp(sys_date.getTime()));
            a.setName("WIKI/AAPL");
            a.setDescription("AAPL stock from " + row.getString("Date"));
            System.err.println(a);
            assetListTransformed.add(a);
        }

        return assetListTransformed;
    }

}
