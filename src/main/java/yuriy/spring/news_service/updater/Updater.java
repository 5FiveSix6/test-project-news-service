package yuriy.spring.news_service.updater;

import org.mapstruct.MappingTarget;

public interface Updater <U, E> {

    void update(U updateDto, @MappingTarget E entity);
}
