package yuriy.spring.news_service.service.status_updater.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yuriy.spring.news_service.dto.news.NewsResponseDto;
import yuriy.spring.news_service.dto.news.NewsUpdateStatusDto;
import yuriy.spring.news_service.entity.News;
import yuriy.spring.news_service.entity.Role;
import yuriy.spring.news_service.entity.Status;
import yuriy.spring.news_service.exception.InvalidUserRoleException;
import yuriy.spring.news_service.mapper.NewsMapper;
import yuriy.spring.news_service.service.UserService;
import yuriy.spring.news_service.service.status_updater.NewsStatusUpdateStrategy;

@Component
@RequiredArgsConstructor
public class PublishNewsStrategy implements NewsStatusUpdateStrategy {

    private final UserService userService;
    private final NewsMapper newsMapper;

    @Override
    public Status getStatus() {
        return Status.READY_TO_PUBLICATION;
    }

    @Override
    public NewsResponseDto update(NewsUpdateStatusDto newsDto, News news) {
        var author = userService.validateUserRole(newsDto.getSenderId(), Role.OPERATOR);
        if (!news.getAuthor().equals(author)) {
            throw new InvalidUserRoleException("errors.user.invalid_role");
        }
        news.setStatus(Status.PUBLISHED);
        return newsMapper.toDto(news);
    }
}
