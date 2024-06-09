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
import yuriy.spring.news_service.dto.news.NewsCreateDto;
import yuriy.spring.news_service.dto.news.NewsFilterDto;
import yuriy.spring.news_service.dto.news.NewsResponseDto;
import yuriy.spring.news_service.dto.news.NewsUpdateDto;
import yuriy.spring.news_service.service.NewsService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/news")
@Tag(name = "News Controller", description = "News API")
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    @Operation(summary = "Get list of news. Use request params for filter response.")
    public ResponseEntity<List<NewsResponseDto>> getNews(NewsFilterDto filter) {
        return ResponseEntity.ok(newsService.getAll(filter));
    }

    @PostMapping
    @Operation(summary = "Create new news.")
    public ResponseEntity<NewsResponseDto> createNews(@Valid @RequestBody NewsCreateDto newsDto,
                                                      BindingResult bindingResult,
                                                      UriComponentsBuilder uriBuilder) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        }
        var newsResponseDto = newsService.save(newsDto);
        return ResponseEntity.created(uriBuilder
                .replacePath("/api/v1/news/{id}")
                .build(Map.of("id", newsResponseDto.id())))
                .body(newsResponseDto);
    }

    @GetMapping("/{id:\\d+}")
    @Operation(summary = "Get news by id.")
    public ResponseEntity<NewsResponseDto> getNewsById(@PathVariable Long id) {
        return ResponseEntity.ok(newsService.getById(id));
    }

    @PatchMapping("/{id:\\d+}")
    @Operation(summary = "Update news.")
    public ResponseEntity<NewsResponseDto> updateNews(@PathVariable Long id,
                                                      @Valid @RequestBody NewsUpdateDto newsDto,
                                                      BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException ex) {
                throw ex;
            } else {
                throw new BindException(bindingResult);
            }
        }
        newsDto.setId(id);
        return ResponseEntity.ok(newsService.update(newsDto));
    }

    @DeleteMapping("/{id:\\d+}")
    @Operation(summary = "Delete news by id.")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
