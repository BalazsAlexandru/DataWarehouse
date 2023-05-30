package ro.uvt.info.dw.Repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import ro.uvt.info.dw.DataModel.Asset;

import java.util.List;

public interface AssetRepository extends CassandraRepository<Asset, String> {

}
