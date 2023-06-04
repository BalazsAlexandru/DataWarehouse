package ro.uvt.info.dw.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.uvt.info.dw.DataModel.Asset;
import ro.uvt.info.dw.DataModel.Data;
import ro.uvt.info.dw.DataModel.DataSource;
import ro.uvt.info.dw.Repository.DataSourceRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DataSourceService {

    @Autowired
    DataSourceRepository dataSourceRepository;

    public DataSource getLatestDataSourceByName(String requestedName) {
        List<String> keys = new ArrayList<>();
        keys.add(requestedName);
        List<DataSource> dataSourcesByName = dataSourceRepository.findAllById(keys);
        System.err.println(dataSourcesByName);

        Collections.sort(dataSourcesByName, new Comparator<DataSource>() {
            @Override
            public int compare(DataSource o1, DataSource o2) {
                return o2.getSystem_date().compareTo(o1.getSystem_date());
            }
        });

        return dataSourcesByName.get(0);
    }

}
