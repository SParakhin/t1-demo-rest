package ru.t1.test.demorest.service.impl;

import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.t1.test.demorest.entity.UserEntity;
import ru.t1.test.demorest.mappers.CustomMapper;
import ru.t1.test.demorest.model.UserDto;
import ru.t1.test.demorest.model.UserSearchResult;
import ru.t1.test.demorest.model.UserUpdateRequest;
import ru.t1.test.demorest.repository.UserRepository;
import ru.t1.test.demorest.service.UserService;
import ru.t1.test.demorest.validate.ErrorCode;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final CustomMapper mapper;

  @Override
  public String createUser(UserDto userDto) {
    UserEntity userEntity = mapper.mapUserDtoToUserEntity(userDto);
    return userRepository.save(userEntity).getId().toString();
  }

  @Override
  @Transactional
  public String updateUser(UserUpdateRequest request) {
    Optional<UserEntity> userEntityFromDB = userRepository.findById(request.getUserId());
    if (userEntityFromDB.isPresent()) {
      UserEntity user = userEntityFromDB.get();
      user.setEmail(request.getEmail());
      user.setName(request.getName());
      return user.getId().toString();
    }
    return ErrorCode.USER_NOT_FOUND.getValue();
  }

  @Override
  public UserSearchResult getUser(UUID userId, String phone) {
    Optional<UserEntity> userEntityFromDB = userRepository.getByIdAndPhone(userId, phone);
    if (userEntityFromDB.isPresent()) {
      UserEntity user = userEntityFromDB.get();
      return mapper.mapUserEntityToUserSearchResult(user);
    }
    return UserSearchResult.builder().errorCode(ErrorCode.USER_NOT_FOUND.getValue()).build();
  }

  @Override
  public void removeUser(UUID userId) {
    userRepository.deleteById(userId);
  }

}
