package ro.uvt.info.dw.Repository;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import ro.uvt.info.dw.DataModel.Asset;

import java.util.List;

public interface AssetRepository extends CassandraRepository<Asset, Integer> {
    @AllowFiltering
    List<Asset> findById(int id);

    //@AllowFiltering
    @Query("SELECT * FROM asset WHERE id > ?1 AND id < ?2")
    List<Asset> findBetweenRangeOfIds(int lowerBound, int upperBound);

    @AllowFiltering
    @Query("SELECT * FROM asset WHERE id >= ?1 LIMIT ?2;")
    List<Asset> findPaginatedResults(int offset, int limit);
}
