package ro.uvt.info.dw.Repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import ro.uvt.info.dw.DataModel.Data;

public interface DataRepository extends CassandraRepository<Data, Integer> {
}
