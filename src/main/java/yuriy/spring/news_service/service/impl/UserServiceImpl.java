package yuriy.spring.news_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yuriy.spring.news_service.dto.user.UserCreateDto;
import yuriy.spring.news_service.dto.user.UserResponseDto;
import yuriy.spring.news_service.dto.user.UserUpdateDto;
import yuriy.spring.news_service.entity.Role;
import yuriy.spring.news_service.entity.User;
import yuriy.spring.news_service.exception.InvalidUserRoleException;
import yuriy.spring.news_service.exception.UserAlreadyExistException;
import yuriy.spring.news_service.exception.UserNotFoundException;
import yuriy.spring.news_service.mapper.UserMapper;
import yuriy.spring.news_service.repository.UserRepository;
import yuriy.spring.news_service.service.UserService;
import yuriy.spring.news_service.updater.UserUpdater;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserUpdater userUpdater;

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto save(UserCreateDto userDto) {
        if (userRepository.existsByFirstNameAndLastNameAndMiddleName(
                userDto.firstName(),
                userDto.lastName(),
                userDto.middleName())) {
            throw new UserAlreadyExistException("errors.user.user_already_exist");
        }
        var user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponseDto update(UserUpdateDto userDto) {
        var user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException("errors.user.not_found"));
        userUpdater.update(userDto, user);
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAll() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    @Override
    public UserResponseDto getById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("errors.user.not_found"));
        return userMapper.toDto(user);
    }

    @Override
    public User validateUserRole(Long id, Role role) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("errors.user.not_found"));
        if (!user.getRole().equals(role)) {
            throw new InvalidUserRoleException("errors.user.invalid_specified_role");
        }
        return user;
    }
}
