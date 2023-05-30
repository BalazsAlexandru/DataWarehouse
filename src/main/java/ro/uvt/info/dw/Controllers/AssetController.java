package ro.uvt.info.dw.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.dw.DataModel.Asset;
import ro.uvt.info.dw.Quandl.QuandlService;
import ro.uvt.info.dw.Repository.AssetRepository;
import ro.uvt.info.dw.Services.AssetService;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/create-asset")
    public Asset createAsset(@RequestBody Asset asset) {
        assetRepository.save(asset);
        System.out.println("Created asset entry: " + asset);
        return asset;
    }

    @DeleteMapping("/delete-asset-by-id/{id}")
    public void deleteAssetById(@PathVariable("id") String name) {
        assetRepository.deleteById(name);
        System.out.println("Deleted asset entry with id: " + name);
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
    public void injectQuandlData(@RequestParam(required = false) String stockLabel) {
        quandlService.transformAndSaveAsset(stockLabel);
        System.out.println("Data imported successfully for " + stockLabel);
    }

    @GetMapping("/getPaginatedAssets")
    public List<String> getPaginatedAssets(@RequestParam(required = false) Optional<Integer> offset,
                          @RequestParam(required = false) Optional<Integer> limit) {
        return assetService.getPaginatedAssets(offset, limit);
    }

    @GetMapping("/getLatestAssetByName")
    public Asset getLatestAssetByName(@RequestParam(required = true) String requestedName){
        return assetService.getLatestAssetByName(requestedName);
    }

}
