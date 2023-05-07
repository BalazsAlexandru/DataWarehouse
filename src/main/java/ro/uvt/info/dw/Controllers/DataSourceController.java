package ro.uvt.info.dw.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.uvt.info.dw.DataModel.DataSource;
import ro.uvt.info.dw.Repository.DataSourceRepository;

import java.util.List;

@RestController
@RequestMapping("/dataSource")
public class DataSourceController {

    @Autowired
    DataSourceRepository dataSourceRepository;;

    @GetMapping("/get-all-sources")
    public List<DataSource> getAllSources() {
        List<DataSource> sourceFetchedList = dataSourceRepository.findAll();
        return sourceFetchedList;
    }

    @GetMapping("/get-source-by-ids/{id}")
    public List<DataSource> getSourceById(@PathVariable List<Integer> ids) {
        List<DataSource> sourceFetchedList = dataSourceRepository.findAllById(ids);
        return sourceFetchedList;
    }

    @PostMapping("/create-source")
    public DataSource createsource(@RequestBody DataSource source) {
        dataSourceRepository.save(source);
        System.out.println("Created source entry: " + source);
        return source;
    }

    @DeleteMapping("/delete-source-by-id/{id}")
    public void deletesourceById(@PathVariable("id") Integer id) {
        dataSourceRepository.deleteById(id);
        System.out.println("Deleted source entry with id: " + id);
    }

    @DeleteMapping("/delete-all-sources")
    public void deleteAllsources() {
        dataSourceRepository.deleteAll();
        System.out.println("Deleted all source entries");
    }

    @GetMapping("/count")
    public Long getNumberOfsourceEntries() {
        return dataSourceRepository.count();
    }
}
