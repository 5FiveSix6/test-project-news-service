package yuriy.spring.news_service.service;

import yuriy.spring.news_service.dto.news.NewsCreateDto;
import yuriy.spring.news_service.dto.news.NewsFilterDto;
import yuriy.spring.news_service.dto.news.NewsResponseDto;
import yuriy.spring.news_service.dto.news.NewsUpdateDto;
import yuriy.spring.news_service.dto.news.NewsUpdateStatusDto;

import java.util.List;

public interface NewsService {

    List<NewsResponseDto> getAll(NewsFilterDto filter);

    NewsResponseDto getById(Long id);

    NewsResponseDto save(NewsCreateDto newsDto);

    void delete(Long id);

    NewsResponseDto update(NewsUpdateDto newsDto);

    NewsResponseDto updateStatus(NewsUpdateStatusDto newsDto);
}
