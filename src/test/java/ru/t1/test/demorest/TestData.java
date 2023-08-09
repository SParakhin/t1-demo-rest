package ru.t1.test.demorest;

import java.util.UUID;
import ru.t1.test.demorest.entity.UserEntity;
import ru.t1.test.demorest.model.UserDto;

public class TestData {

  public static UserEntity getUserEntity() {
    return UserEntity.builder()
        .id(UUID.randomUUID())
        .email("ormts@mail.ru")
        .name("Sergey Parakhin")
        .phone("79038824965")
        .build();
  }

  public static UserDto getUserDto() {
    return UserDto.builder()
        .email("ormts@mail.ru")
        .name("Sergey Parakhin")
        .phone("79038824965")
        .build();
  }

}
