package ru.t1.test.demorest.service;

import java.util.UUID;
import ru.t1.test.demorest.entity.UserEntity;
import ru.t1.test.demorest.model.UserDto;
import ru.t1.test.demorest.model.UserSearchResult;
import ru.t1.test.demorest.model.UserUpdateRequest;

public interface UserService {

  String createUser(UserDto userDto);

  String updateUser(UserUpdateRequest request);

  UserSearchResult getUser(UUID userId, String phone);

  void removeUser(UUID userId);
}
