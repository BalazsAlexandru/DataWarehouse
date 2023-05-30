package ro.uvt.info.dw.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.uvt.info.dw.DataModel.Asset;
import ro.uvt.info.dw.Repository.AssetRepository;

import java.util.*;

@Service
public class AssetService {

    @Autowired
    AssetRepository assetRepository;

    public List<String> getPaginatedAssets(Optional<Integer> offsetReceived, Optional<Integer> limitReceived){
        // Sets the default values
        int offset = offsetReceived.orElse(0);
        int limit = limitReceived.orElse(20);

        Set<String> savedAssetNames = new HashSet<>();
        List<Asset> existingAssets = assetRepository.findAll();

        for(Asset a : existingAssets){
            savedAssetNames.add(a.getName());
        }

        List<String> sortedList = new ArrayList<>(savedAssetNames);
        Collections.sort(sortedList);

        List<String> result = new ArrayList<>();

        for (int i = offset; i <= limit; i++) {
            result.add(sortedList.get(i));
        }

        return result;
    }

    public Asset getLatestAssetByName(String requestedName) {
        List<String> keys = new ArrayList<>();
        keys.add(requestedName);
        List<Asset> assetsByName = assetRepository.findAllById(keys);
        System.err.println(assetsByName);

        Collections.sort(assetsByName, new Comparator<Asset>() {
            @Override
            public int compare(Asset o1, Asset o2) {
                return o2.getSystem_date().compareTo(o1.getSystem_date());
            }
        });

        return assetsByName.get(0);
    }
}
