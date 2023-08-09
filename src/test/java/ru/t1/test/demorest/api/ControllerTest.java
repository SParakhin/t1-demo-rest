package ru.t1.test.demorest.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import ru.t1.test.demorest.TestData;
import ru.t1.test.demorest.model.UserDto;
import ru.t1.test.demorest.service.UserService;
import ru.t1.test.demorest.validate.UniqueValidator;

@SpringBootTest
class ControllerTest {

  @Mock
  private UserService userService;

  @Mock
  private UniqueValidator validator;

  @InjectMocks
  private Controller controller;

  @Test
  public void testCreateUser_ValidRequest() {
    UserDto userDto = TestData.getUserDto();

    BindingResult bindingResult = mock(BindingResult.class);
    when(bindingResult.hasErrors()).thenReturn(false);

    ResponseEntity<String> expectedResponse = ResponseEntity.ok("User created successfully");

    when(validator.supports(UserDto.class)).thenReturn(true);
    doNothing().when(validator).validate(userDto, bindingResult);

    when(userService.createUser(userDto)).thenReturn("User created successfully");

    ResponseEntity<String> response = controller.createUser(userDto, bindingResult);

    assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
    assertEquals(expectedResponse.getBody(), response.getBody());

    verify(validator, times(1)).validate(userDto, bindingResult);
    verify(userService, times(1)).createUser(userDto);
  }

}