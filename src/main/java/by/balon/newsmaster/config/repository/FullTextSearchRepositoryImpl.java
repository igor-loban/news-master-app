package by.balon.newsmaster.config.repository;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.util.List;

@NoRepositoryBean
public class FullTextSearchRepositoryImpl<E> extends SimpleJpaRepository<E, Long>
        implements FullTextSearchRepository<E> {
    private Class<E> domainClass;
    private EntityManager entityManager;

    public FullTextSearchRepositoryImpl(Class<E> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
        this.entityManager = entityManager;
    }

    @Override
    public List<E> search(String field, String query) {
        if (query == null) {
            query = "";
        }

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(domainClass).get();

        try {
            Query luceneQuery = queryBuilder.keyword().onField(field).matching(query.toLowerCase()).createQuery();

            FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, domainClass);

            //noinspection unchecked
            return (List<E>) fullTextQuery.getResultList();
        } catch (Exception e) {
            return findAll();
        }
    }
}
