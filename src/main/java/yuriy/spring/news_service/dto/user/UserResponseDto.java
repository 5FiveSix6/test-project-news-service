package yuriy.spring.news_service.dto.user;

import yuriy.spring.news_service.entity.Role;

public record UserResponseDto(Long id,
                              String firstName,
                              String lastName,
                              String middleName,
                              Role role) {
}
