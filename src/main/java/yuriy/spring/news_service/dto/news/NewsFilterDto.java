package yuriy.spring.news_service.dto.news;

import yuriy.spring.news_service.entity.Status;

public record NewsFilterDto(Long authorId,
                            Status status,
                            Long moderatorId) {
}
