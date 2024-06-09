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
public class UpdateToModeratedStrategy implements NewsStatusUpdateStrategy {

    private final UserService userService;
    private final NewsMapper newsMapper;

    @Override
    public Status getStatus() {
        return Status.MODERATION;
    }

    @Override
    public NewsResponseDto update(NewsUpdateStatusDto newsDto, News news) {
        var moderator = userService.validateUserRole(newsDto.getModeratorId(), Role.MODERATOR);
        if (!news.getModerator().equals(moderator)) {
            throw new InvalidUserRoleException("errors.user.invalid_role");
        }
        news.setStatus(Status.READY_TO_PUBLICATION);
        return newsMapper.toDto(news);
    }
}
