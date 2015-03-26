package by.balon.newsmaster.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;

public class FullTextSearchRepositoryFactoryBean<E, R extends JpaRepository<E, Long>>
        extends JpaRepositoryFactoryBean<R, E, Long> {
    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new FullTextSearchRepositoryFactory(entityManager);
    }
}
