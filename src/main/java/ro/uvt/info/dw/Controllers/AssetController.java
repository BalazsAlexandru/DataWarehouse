package ro.uvt.info.dw.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.dw.DataModel.Asset;
import ro.uvt.info.dw.Quandl.QuandlService;
import ro.uvt.info.dw.Repository.AssetRepository;
import ro.uvt.info.dw.Services.AssetService;

import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    AssetRepository assetRepository;

    @Autowired
    QuandlService quandlService;

    @Autowired
    AssetService assetService;

    @GetMapping(path = "/get-all-assets", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Asset> getAllAssets() {
        List<Asset> assetFetchedList = assetRepository.findAll();
        return assetFetchedList;
    }

    // Not working at the moment
    @GetMapping(path = "/get-all-assets-xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Asset> getAllAssetsXML() {
        List<Asset> assetFetchedList = assetRepository.findAll();
        return assetFetchedList;
    }

    @GetMapping(path = "/get-all-assets-json", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Asset> getAllAssetsJSON() {
        List<Asset> assetFetchedList = assetRepository.findAll();
        return assetFetchedList;
    }

    @GetMapping("/get-asset-by-id/{id}")
    public List<Asset> getAssetById(@PathVariable("id") int id) {
        List<Asset> assetData = assetRepository.findById(id);
        return assetData;
    }

    @GetMapping("/get-paginated-results")
    public List<Asset> getAssetBetweenIdRange() {
        List<Asset> assetData = assetRepository.findPaginatedResults(9,20);
        return assetData;
    }

    @PostMapping("/create-asset")
    public Asset createAsset(@RequestBody Asset asset) {
        assetRepository.save(asset);
        System.out.println("Created asset entry: " + asset);
        return asset;
    }

    @DeleteMapping("/delete-asset-by-id/{id}")
    public void deleteAssetById(@PathVariable("id") Integer id) {
        assetRepository.deleteById(id);
        System.out.println("Deleted asset entry with id: " + id);
    }

    @DeleteMapping("/delete-all-assets")
    public void deleteAllAssets() {
        assetRepository.deleteAll();
        System.out.println("Deleted all asset entries");
    }

    @GetMapping("/count")
    public Long getNumberOfAssetEntries() {
        return assetRepository.count();
    }

    @PostMapping("/load-quandl-data")
    public void injectQuandlData() {
        List<Asset> assetExtractedQuandlData = quandlService.transformAssetData();
        System.out.println(assetExtractedQuandlData);
        assetRepository.saveAll(assetExtractedQuandlData);
    }
//
//    @GetMapping("/asset/{limit}{offset}")
//    public List<Asset> getPaginatedAssets(@PathVariable(value="limit") int limit,
//                                          @PathVariable(value="offset") int offset){
//
//    }

}
