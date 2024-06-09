package yuriy.spring.news_service.mapper;

import java.util.List;

public interface EntityMapper<CD, RD, E> {

    E toEntity(CD createDto);

    RD toDto(E entity);

    List<RD> toDtoList(List<E> entityList);
}
