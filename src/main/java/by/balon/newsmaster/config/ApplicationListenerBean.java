package by.balon.newsmaster.config;

import by.balon.newsmaster.entity.NewsRecord;
import by.balon.newsmaster.repository.NewsRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ApplicationListenerBean implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private NewsRecordRepository newsRecordRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        NewsRecord newsRecord = new NewsRecord();
        newsRecord.setTitle("Title 1");
        newsRecord.setAuthor("Igor Loban");
        newsRecord.setText("aaa bbb ccc");
        newsRecord.setPublicationDate(new Date().getTime());
        newsRecordRepository.save(newsRecord);

        newsRecord = new NewsRecord();
        newsRecord.setTitle("Title 2");
        newsRecord.setAuthor("Igor Loban");
        newsRecord.setText("aaa");
        newsRecord.setPublicationDate(null);
        newsRecordRepository.save(newsRecord);

        newsRecord = new NewsRecord();
        newsRecord.setTitle("Title 3");
        newsRecord.setAuthor("Igor Loban");
        newsRecord.setText("bbb ccc");
        newsRecord.setPublicationDate(123123123123L);
        newsRecordRepository.save(newsRecord);

        newsRecordRepository.flush();
    }
}
