package yuriy.spring.news_service.dto.news;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewsCreateDto(

        @NotNull(message = "{errors.news.author_id_in_null}")
        @Min(value = 1, message = "{errors.invalid_user_id}")
        Long authorId,

        @NotBlank(message = "{errors.news.title_is_blank}")
        @Size(min = 4, max = 255, message = "{errors.news.invalid_title_size}")
        String title,

        @NotBlank(message = "{errors.news.text_is_blank}")
        @Size(min = 6, message = "{errors.news.invalid_text_size}")
        String text) {
}
