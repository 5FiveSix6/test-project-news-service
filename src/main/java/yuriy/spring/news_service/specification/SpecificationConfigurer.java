package yuriy.spring.news_service.specification;

import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import yuriy.spring.news_service.dto.news.NewsFilterDto;
import yuriy.spring.news_service.entity.News;

@Component
public class SpecificationConfigurer {

    public Specification<News> configureNewsSpecification(NewsFilterDto filter) {
        return ((root, query, cb) -> {
            root.fetch("author", JoinType.INNER);
            root.fetch("moderator", JoinType.LEFT);
            if (filter == null) {
                return query.getRestriction();
            } else {
                if (filter.status() != null) {
                    query.where(cb.equal(root.get("status"), filter.status()));
                }
                if (filter.authorId() != null) {
                    query.where(cb.equal(root.get("author").get("id"), filter.authorId()));
                }
                if (filter.moderatorId() != null) {
                    query.where(cb.equal(root.get("moderator").get("id"), filter.moderatorId()));
                }
            }
            return query.getRestriction();
        });
    }
}