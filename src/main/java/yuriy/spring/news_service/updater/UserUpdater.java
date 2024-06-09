package yuriy.spring.news_service.updater;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import yuriy.spring.news_service.dto.user.UserUpdateDto;
import yuriy.spring.news_service.entity.User;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserUpdater extends Updater<UserUpdateDto, User> {
}
