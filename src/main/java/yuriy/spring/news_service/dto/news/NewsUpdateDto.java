package yuriy.spring.news_service.dto.news;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsUpdateDto {

    private Long id;

    @Size(min = 4, max = 255, message = "{errors.news.invalid_title_size}")
    private String title;

    @Size(min = 6, message = "{errors.news.invalid_text_size}")
    private String text;
}
