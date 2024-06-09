package yuriy.spring.news_service.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import yuriy.spring.news_service.entity.News;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

    @Query("""
            select n from News n
            left join fetch n.author
            left join fetch n.moderator
            where n.id = :id
            """)
    Optional<News> findById(Long id);
}
