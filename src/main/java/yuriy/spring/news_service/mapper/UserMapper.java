package yuriy.spring.news_service.mapper;

import org.mapstruct.Mapper;
import yuriy.spring.news_service.dto.user.UserCreateDto;
import yuriy.spring.news_service.dto.user.UserResponseDto;
import yuriy.spring.news_service.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserCreateDto, UserResponseDto, User> {
}
