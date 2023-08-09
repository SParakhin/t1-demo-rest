package ru.t1.test.demorest.mappers;

import org.springframework.stereotype.Component;
import ru.t1.test.demorest.entity.UserEntity;
import ru.t1.test.demorest.model.UserDto;
import ru.t1.test.demorest.model.UserSearchResult;

@Component
public class CustomMapper {

  public UserEntity mapUserDtoToUserEntity(UserDto userDto) {
    return UserEntity.builder()
        .email(userDto.getEmail())
        .name(userDto.getName())
        .phone(userDto.getPhone())
        .build();
  }

  public UserSearchResult mapUserEntityToUserSearchResult(UserEntity entity) {
    return UserSearchResult.builder()
        .userId(entity.getId())
        .email(entity.getEmail())
        .name(entity.getName())
        .phone(entity.getPhone())
        .build();
  }

}
