package yuriy.spring.news_service.service.status_updater.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import yuriy.spring.news_service.dto.news.NewsResponseDto;
import yuriy.spring.news_service.dto.news.NewsUpdateStatusDto;
import yuriy.spring.news_service.entity.News;
import yuriy.spring.news_service.entity.Role;
import yuriy.spring.news_service.entity.Status;
import yuriy.spring.news_service.exception.InvalidModeratorIdException;
import yuriy.spring.news_service.exception.InvalidUserRoleException;
import yuriy.spring.news_service.mapper.NewsMapper;
import yuriy.spring.news_service.service.UserService;
import yuriy.spring.news_service.service.status_updater.NewsStatusUpdateStrategy;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SendToModerationStrategy implements NewsStatusUpdateStrategy {

    private final UserService userService;
    private final NewsMapper newsMapper;

    @Override
    public Status getStatus() {
        return Status.DRAFT;
    }

    @Override
    public NewsResponseDto update(NewsUpdateStatusDto newsDto, News news) {
        if (newsDto.getModeratorId() == null) {
            throw new InvalidModeratorIdException("errors.user.moderator_id_is_null");
        }
        if (Objects.equals(newsDto.getModeratorId(), newsDto.getSenderId())) {
            throw new InvalidUserRoleException("errors.user.user_already_author_or_moderator");
        }
        var author = userService.validateUserRole(newsDto.getSenderId(), Role.OPERATOR);
        if (!news.getAuthor().equals(author)) {
            throw new InvalidUserRoleException("errors.user.invalid_role");
        }
        var moderator = userService.validateUserRole(newsDto.getModeratorId(), Role.MODERATOR);
        news.setModerator(moderator);
        news.setStatus(Status.MODERATION);
        return newsMapper.toDto(news);
    }
}
