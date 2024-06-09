package yuriy.spring.news_service.updater;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import yuriy.spring.news_service.dto.news.NewsUpdateDto;
import yuriy.spring.news_service.entity.News;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsUpdater extends Updater<NewsUpdateDto, News> {

}
