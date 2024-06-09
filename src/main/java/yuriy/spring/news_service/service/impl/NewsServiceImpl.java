package yuriy.spring.news_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yuriy.spring.news_service.dto.news.NewsCreateDto;
import yuriy.spring.news_service.dto.news.NewsFilterDto;
import yuriy.spring.news_service.dto.news.NewsResponseDto;
import yuriy.spring.news_service.dto.news.NewsUpdateDto;
import yuriy.spring.news_service.dto.news.NewsUpdateStatusDto;
import yuriy.spring.news_service.entity.Role;
import yuriy.spring.news_service.entity.Status;
import yuriy.spring.news_service.exception.NewsAlreadyPublishedException;
import yuriy.spring.news_service.exception.NewsNotFoundException;
import yuriy.spring.news_service.mapper.NewsMapper;
import yuriy.spring.news_service.repository.NewsRepository;
import yuriy.spring.news_service.service.NewsService;
import yuriy.spring.news_service.service.UserService;
import yuriy.spring.news_service.service.status_updater.NewsStatusUpdateStrategy;
import yuriy.spring.news_service.specification.SpecificationConfigurer;
import yuriy.spring.news_service.updater.NewsUpdater;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final UserService userService;
    private final NewsMapper newsMapper;
    private final SpecificationConfigurer specificationConfigurer;
    private final NewsUpdater newsUpdater;
    private final Map<Status, NewsStatusUpdateStrategy> statusUpdateStrategies;

    @Override
    public List<NewsResponseDto> getAll(NewsFilterDto filter) {
        var spec = specificationConfigurer.configureNewsSpecification(filter);
        var newsList = newsRepository.findAll(spec);
        return newsMapper.toDtoList(newsList);
    }

    @Override
    public NewsResponseDto getById(Long id) {
        var news = newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("errors.news.not_found"));
        return newsMapper.toDto(news);
    }

    @Override
    @Transactional
    public NewsResponseDto save(NewsCreateDto newsDto) {
        var author = userService.validateUserRole(newsDto.authorId(), Role.OPERATOR);
        var news = newsMapper.toEntity(newsDto);
        news.setAuthor(author);
        news.setStatus(Status.DRAFT);
        return newsMapper.toDto(newsRepository.save(news));
    }

    @Override
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public NewsResponseDto update(NewsUpdateDto newsDto) {
        var news = newsRepository.findById(newsDto.getId())
                .orElseThrow(() -> new NewsNotFoundException("errors.news.not_found"));
        newsUpdater.update(newsDto, news);
        return newsMapper.toDto(news);
    }

    @Override
    @Transactional
    public NewsResponseDto updateStatus(NewsUpdateStatusDto newsDto) {
        var news = newsRepository.findById(newsDto.getId())
                .orElseThrow(() -> new NewsNotFoundException("errors.news.not_found"));
        var statusUpdateStrategy = statusUpdateStrategies.get(news.getStatus());
        if (statusUpdateStrategy == null) {
            throw new NewsAlreadyPublishedException("errors.news.news_already_published");
        }
        return statusUpdateStrategy.update(newsDto, news);
    }
}
