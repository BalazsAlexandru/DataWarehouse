package ro.uvt.info.dw.MongoControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.dw.MongoDataModel.AssetMongo;
import ro.uvt.info.dw.MongoRepository.AssetMongoRepository;

import java.util.List;

@RestController
@RequestMapping("/assetMongo")
public class AssetMongoController {

//    @Autowired
//    AssetMongoRepository assetMongoRepository;
//
//    @GetMapping(path = "/get-all-assets-mongo", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<AssetMongo> getAllAssets() {
//        List<AssetMongo> assetMongoFetchedList = assetMongoRepository.findAll();
//        return assetMongoFetchedList;
//    }


}
