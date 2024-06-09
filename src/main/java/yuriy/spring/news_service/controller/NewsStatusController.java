package yuriy.spring.news_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yuriy.spring.news_service.dto.news.NewsResponseDto;
import yuriy.spring.news_service.dto.news.NewsUpdateStatusDto;
import yuriy.spring.news_service.service.NewsService;

@RestController
@RequestMapping("api/v1/news/{id:\\d+}/change-status")
@RequiredArgsConstructor
@Tag(name = "News Status Controller", description = "API for change news status from DRAFT to PUBLISHED")
public class NewsStatusController {

    private final NewsService newsService;

    @PatchMapping
    @Operation(summary = """
            Change the status of a news article. Only a user with the role "OPERATOR" who is also the author of the news
            article can change a news article with the status "DRAFT", and the request body must include 
            the moderator ID.
            Only a user with the role "MODERATOR" who is also the moderator of the news article can change a news 
            article with the status "MODERATION".
            Only a user with the role "OPERATOR" who is also the author of the news article can change a news article 
            with the status "READY_FOR_PUBLICATION".
            Changing a news article with the status "PUBLISHED" will result in an error.
            """)
    public ResponseEntity<NewsResponseDto> changeNewsStatus(@PathVariable("id") Long id,
                                                            @Valid @RequestBody NewsUpdateStatusDto newsDto,
                                                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        }
        newsDto.setId(id);
        return ResponseEntity.ok(newsService.updateStatus(newsDto));
    }
}
