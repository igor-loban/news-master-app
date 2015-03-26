package by.balon.newsmaster.controller;

import by.balon.newsmaster.entity.NewsRecord;
import by.balon.newsmaster.repository.NewsRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news-records")
public class NewsRecordController {
    @Autowired
    private NewsRecordRepository newsRecordRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody NewsRecord newsRecord) {
        newsRecordRepository.save(newsRecord);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NewsRecord> search(@RequestBody Query query) {
        return newsRecordRepository.search("text", query.getValue());
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void remove(@RequestBody Long id) {
        newsRecordRepository.delete(id);
    }

    private static final class Query {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
