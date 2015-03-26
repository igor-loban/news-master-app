package by.balon.newsmaster.test;

import by.balon.newsmaster.config.NewsMasterConfig;
import by.balon.newsmaster.entity.NewsRecord;
import by.balon.newsmaster.repository.NewsRecordRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NewsMasterConfig.class)
public class SimpleTest {
    @Autowired
    private NewsRecordRepository newsRecordRepository;

    @Test
    public void test() {
        Assert.assertNotNull(newsRecordRepository);
        Assert.assertEquals(3, newsRecordRepository.count());

        List<NewsRecord> newsRecords = newsRecordRepository.search("text", "aaa");
        Assert.assertEquals(2, newsRecords.size());

        newsRecords = newsRecordRepository.search("text", "bbb");
        Assert.assertEquals(2, newsRecords.size());

        newsRecords = newsRecordRepository.search("text", "ccc");
        Assert.assertEquals(2, newsRecords.size());

        newsRecords = newsRecordRepository.search("text", "aaa ccc");
        Assert.assertEquals(3, newsRecords.size());
    }
}
