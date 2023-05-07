package ro.uvt.info.dw.Repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ro.uvt.info.dw.DataModel.DataSource;

public interface DataSourceRepository extends CassandraRepository<DataSource, Integer> {
}
