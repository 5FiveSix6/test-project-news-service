package yuriy.spring.news_service.service.status_updater;

import yuriy.spring.news_service.dto.news.NewsResponseDto;
import yuriy.spring.news_service.dto.news.NewsUpdateStatusDto;
import yuriy.spring.news_service.entity.News;
import yuriy.spring.news_service.entity.Status;

public interface NewsStatusUpdateStrategy {

    Status getStatus();

    NewsResponseDto update(NewsUpdateStatusDto newsDto, News news);
}
