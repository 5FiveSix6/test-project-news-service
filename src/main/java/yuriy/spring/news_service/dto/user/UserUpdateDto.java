package yuriy.spring.news_service.dto.user;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yuriy.spring.news_service.entity.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {

    private Long id;

    @Size(min = 2, max = 32, message = "{errors.user.first_name_size_is_invalid}")
    private String firstName;

    @Size(min = 4, max = 32, message = "{errors.user.last_name_size_is_invalid}")
    private String lastName;

    @Size(min = 4, max = 32, message = "{errors.user.middle_name_size_is_invalid}")
    private String middleName;

    private Role role;
}
