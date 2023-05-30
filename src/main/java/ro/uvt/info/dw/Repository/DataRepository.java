package ro.uvt.info.dw.Repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import ro.uvt.info.dw.DataModel.Data;

import java.util.List;

public interface DataRepository extends CassandraRepository<Data, String> {

    @AllowFiltering
    List<Data> findAllByAssetId(String assetId);
}
