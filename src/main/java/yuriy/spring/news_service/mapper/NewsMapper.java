package yuriy.spring.news_service.mapper;

import org.mapstruct.Mapper;
import yuriy.spring.news_service.dto.news.NewsCreateDto;
import yuriy.spring.news_service.dto.news.NewsResponseDto;
import yuriy.spring.news_service.entity.News;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface NewsMapper extends EntityMapper<NewsCreateDto, NewsResponseDto, News> {
}
