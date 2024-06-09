package yuriy.spring.news_service.dto.news;

import yuriy.spring.news_service.dto.user.UserResponseDto;
import yuriy.spring.news_service.entity.Status;

public record NewsResponseDto(Long id,
                              String title,
                              String text,
                              UserResponseDto author,
                              UserResponseDto moderator,
                              Status status) {
}
