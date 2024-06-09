package yuriy.spring.news_service.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import yuriy.spring.news_service.entity.Role;

public record UserCreateDto(

        @NotBlank(message = "{errors.user.first_name_is_blank}")
        @Size(min = 2, max = 32, message = "{errors.user.first_name_size_is_invalid}")
        String firstName,

        @NotBlank(message = "{errors.user.last_name_is_blank}")
        @Size(min = 4, max = 32, message = "{errors.user.last_name_size_is_invalid}")
        String lastName,

        @Size(min = 4, max = 32, message = "{errors.user.middle_name_size_is_invalid}")
        String middleName,

        @NotBlank(message = "{errors.user.role_is_blank}")
        Role role) {
}
