package yuriy.spring.news_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import yuriy.spring.news_service.dto.user.UserCreateDto;
import yuriy.spring.news_service.dto.user.UserResponseDto;
import yuriy.spring.news_service.dto.user.UserUpdateDto;
import yuriy.spring.news_service.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
@Tag(name = "User Controller", description = "User API")
public class UsersController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get list of users.")
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PostMapping
    @Operation(summary = "Create new user.")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateDto userDto,
                                                      BindingResult bindingResult,
                                                      UriComponentsBuilder uriBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        }
        var userResponseDto = userService.save(userDto);
        return ResponseEntity.created(uriBuilder
                .replacePath("/api/v1/users/{id}")
                .build(Map.of("id", userResponseDto.id())))
                .body(userResponseDto);
    }

    @GetMapping("/{id:\\d+}")
    @Operation(summary = "Get user by id.")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PatchMapping("/{id:\\d+}")
    @Operation(summary = "Update user.")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                      @Valid @RequestBody UserUpdateDto userDto,
                                                      BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        }
        userDto.setId(id);
        return ResponseEntity.ok(userService.update(userDto));
    }

    @DeleteMapping("/{id:\\d+}")
    @Operation(summary = "Delete user by id.")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
