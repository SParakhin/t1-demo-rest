package ru.t1.test.demorest.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.t1.test.demorest.TestData;
import ru.t1.test.demorest.entity.UserEntity;
import ru.t1.test.demorest.mappers.CustomMapper;
import ru.t1.test.demorest.model.UserDto;
import ru.t1.test.demorest.model.UserSearchResult;
import ru.t1.test.demorest.model.UserUpdateRequest;
import ru.t1.test.demorest.repository.UserRepository;
import ru.t1.test.demorest.validate.ErrorCode;

@SpringBootTest
class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private CustomMapper mapper;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  public void testCreateUser() {
    UserDto userDto = TestData.getUserDto();
    ;

    UserEntity userEntity = TestData.getUserEntity();

    when(userRepository.save(userEntity)).thenReturn(userEntity);
    when(mapper.mapUserDtoToUserEntity(userDto)).thenReturn(userEntity);

    String userId = userService.createUser(userDto);

    assertNotNull(userId);
    assertFalse(userId.isEmpty()); // Проверка, что идентификатор пользователя не пустой
  }

  @Test
  public void testUpdateUser() {
    UUID userId = UUID.randomUUID();
    String email = "newemail@example.com";
    String name = "New Name";

    UserUpdateRequest request = new UserUpdateRequest();
    request.setUserId(userId);
    request.setEmail(email);
    request.setName(name);

    UserEntity userEntity = TestData.getUserEntity();
    userEntity.setId(userId);
    userEntity.setEmail("oldemail@example.com");
    userEntity.setName("Old Name");

    when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));

    String updatedUserId = userService.updateUser(request);

    assertNotNull(updatedUserId);
    assertEquals(userId.toString(), updatedUserId);
    assertEquals(email, userEntity.getEmail());
    assertEquals(name, userEntity.getName());
  }

  @Test
  public void testGetUser_UserFound() {
    UUID userId = UUID.randomUUID();
    String phone = "1234567890";

    UserEntity userEntity = TestData.getUserEntity();
    UserSearchResult userSearchResult = UserSearchResult.builder()
        .userId(userEntity.getId())
        .phone(userEntity.getPhone())
        .build();

    when(userRepository.getByIdAndPhone(userId, phone)).thenReturn(Optional.of(userEntity));
    when(mapper.mapUserEntityToUserSearchResult(userEntity)).thenReturn(userSearchResult);

    UserSearchResult result = userService.getUser(userId, phone);

    assertNotNull(result);
    assertEquals(userEntity.getId(), result.getUserId());
  }

  @Test
  public void testGetUser_UserNotFound() {
    UUID userId = UUID.randomUUID();
    String phone = "1234567890";

    when(userRepository.getByIdAndPhone(userId, phone)).thenReturn(Optional.empty());

    UserSearchResult result = userService.getUser(userId, phone);

    assertNotNull(result);
    assertEquals(ErrorCode.USER_NOT_FOUND.getValue(), result.getErrorCode());
  }

}
