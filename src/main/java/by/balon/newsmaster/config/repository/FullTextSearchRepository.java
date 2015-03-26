package by.balon.newsmaster.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FullTextSearchRepository<E> extends JpaRepository<E, Long> {
    List<E> search(String field, String query);
}
