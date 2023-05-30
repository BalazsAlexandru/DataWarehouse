package ro.uvt.info.dw.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ro.uvt.info.dw.DataModel.Data;
import ro.uvt.info.dw.Services.DataService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/getData")
    public List<Data> getData(@RequestParam(required = false) Optional<String> assetId,
                              @RequestParam(required = false) Optional<String> dataSourceId,
                              @RequestParam(required = false) Optional<String> startBusinessDate,
                              @RequestParam(required = false) Optional<String> endBusinessDate,
                              @RequestParam(required = false) Optional<Boolean> includeAttributes) throws ParseException {
        return dataService.getData(assetId, dataSourceId, startBusinessDate, endBusinessDate, includeAttributes);
    }
}
