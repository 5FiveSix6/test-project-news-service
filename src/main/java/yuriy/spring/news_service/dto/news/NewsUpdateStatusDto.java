package yuriy.spring.news_service.dto.news;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsUpdateStatusDto {

    private Long id;

    @NotNull(message = "{errors.news.sender_id_is_null}")
    @Min(value = 1, message = "{errors.invalid_user_id}")
    private Long senderId;

    @Min(value = 1, message = "{errors.invalid_user_id}")
    private Long moderatorId;
}
