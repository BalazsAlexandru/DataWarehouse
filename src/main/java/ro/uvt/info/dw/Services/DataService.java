package ro.uvt.info.dw.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.uvt.info.dw.DataModel.Data;
import ro.uvt.info.dw.Repository.DataRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    @Autowired
    DataRepository dataRepository;

    public List<Data> getData(Optional<String> assetIdReceived,
                              Optional<String> dataSourceIdReceived,
                              Optional<String> startBusinessDateReceived,
                              Optional<String> endBusinessDateReceived,
                              Optional<Boolean> includeAttributesReceived) throws ParseException {

        String assetId = assetIdReceived.orElse("Unknown");
        String dataSourceId = dataSourceIdReceived.orElse("Unknown");
        String startBusinessDateString = startBusinessDateReceived.orElse("01/01/2017");
        String endBusinessDateString = endBusinessDateReceived.orElse("01/01/2019");
        Boolean includeAttributes = includeAttributesReceived.orElse(false);

        Date startBusinessDate = new SimpleDateFormat("dd/MM/yyyy").parse(startBusinessDateString);
        Date endBusinessDate = new SimpleDateFormat("dd/MM/yyyy").parse(endBusinessDateString);

        System.err.println(startBusinessDate);
        System.err.println(endBusinessDate);

        List<Data> fetchedData = new ArrayList<>();

        fetchedData = dataRepository.findAllByAssetId(assetId);

        List<Data> dataFiltered = new ArrayList<>();

        for(Data d : fetchedData){
            if(!d.getDataSourceId().equals("Unknown")
                    && d.getDataSourceId().equals(dataSourceId)
                    && d.getBusinessDate().after(startBusinessDate)
                    && d.getBusinessDate().before(endBusinessDate)){
                dataFiltered.add(d);
            }
        }

        List<Data> dataListResulted = new ArrayList<>();

        if(includeAttributes == false){
            for(Data d : dataFiltered){
                Data dataNoAttributes = new Data(d.getAssetId(), d.getDataSourceId(), d.getBusinessDate(), d.getSystemDate());
                dataListResulted.add(dataNoAttributes);
            }
            return dataListResulted;
        }
        else{
            return dataFiltered;
        }
    }

}
