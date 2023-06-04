package ro.uvt.info.dw.MongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ro.uvt.info.dw.MongoDataModel.AssetMongo;

@Repository
public interface AssetMongoRepository extends MongoRepository<AssetMongo, String> {
}
