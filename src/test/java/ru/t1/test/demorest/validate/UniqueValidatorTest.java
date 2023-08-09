package ru.t1.test.demorest.validate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;
import ru.t1.test.demorest.TestData;
import ru.t1.test.demorest.model.UserDto;
import ru.t1.test.demorest.repository.UserRepository;

@SpringBootTest
class UniqueValidatorTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private Errors errors;

  @InjectMocks
  private UniqueValidator uniqueValidator;

  @Test
  public void testValidate_UniqueEmail() {
    UserDto userDto = TestData.getUserDto();
    userDto.setEmail("test@example.com");

    when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);

    uniqueValidator.validate(userDto, errors);

    verify(errors, never()).rejectValue(eq("email"), anyString());
  }

  @Test
  public void testValidate_DuplicateEmail() {
    UserDto userDto = TestData.getUserDto();
    userDto.setEmail("test@example.com");

    when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(true);

    uniqueValidator.validate(userDto, errors);

    verify(errors).reject("EMAIL", ErrorCode.EMAIL.getValue());
  }

  @Test
  public void testValidate_UniquePhone() {
    UserDto userDto = TestData.getUserDto();
    userDto.setPhone("1234567890");

    when(userRepository.existsByPhone(userDto.getPhone())).thenReturn(false);

    uniqueValidator.validate(userDto, errors);

    verify(errors, never()).rejectValue(eq("PHONE"), anyString());
  }

  @Test
  public void testValidate_DuplicatePhone() {
    UserDto userDto = TestData.getUserDto();
    userDto.setPhone("1234567890");

    when(userRepository.existsByPhone(userDto.getPhone())).thenReturn(true);

    uniqueValidator.validate(userDto, errors);

    verify(errors).reject("PHONE", ErrorCode.PHONE.getValue());
  }

  @Test
  public void testValidate_InvalidName() {
    UserDto userDto = TestData.getUserDto();
    userDto.setName("John");

    uniqueValidator.validate(userDto, errors);

    verify(errors).reject("NAME", ErrorCode.NAME.getValue());
  }

  @Test
  public void testValidate_ValidName() {
    UserDto userDto = TestData.getUserDto();
    userDto.setName("John Doe");

    uniqueValidator.validate(userDto, errors);

    verify(errors, never()).rejectValue(eq("NAME"), anyString());
  }

}