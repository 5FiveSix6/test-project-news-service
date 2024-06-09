package yuriy.spring.news_service.service;

import yuriy.spring.news_service.dto.user.UserCreateDto;
import yuriy.spring.news_service.dto.user.UserResponseDto;
import yuriy.spring.news_service.dto.user.UserUpdateDto;
import yuriy.spring.news_service.entity.Role;
import yuriy.spring.news_service.entity.User;

import java.util.List;

public interface UserService {

    void delete(Long id);

    UserResponseDto save(UserCreateDto userDto);

    UserResponseDto update(UserUpdateDto userDto);

    List<UserResponseDto> getAll();

    UserResponseDto getById(Long id);

    User validateUserRole(Long id, Role role);
}
