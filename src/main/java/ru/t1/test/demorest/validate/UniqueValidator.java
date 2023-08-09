package ru.t1.test.demorest.validate;

import static ru.t1.test.demorest.validate.ErrorCode.EMAIL;
import static ru.t1.test.demorest.validate.ErrorCode.NAME;
import static ru.t1.test.demorest.validate.ErrorCode.PHONE;

import java.util.Arrays;
import liquibase.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.t1.test.demorest.model.UserDto;
import ru.t1.test.demorest.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UniqueValidator implements Validator {

  private final UserRepository userRepository;

  @Override
  public boolean supports(Class<?> clazz) {
    return UserDto.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    UserDto userDto = (UserDto) target;
    if (userRepository.existsByEmail(userDto.getEmail())) {
      errors.reject(EMAIL.name(), EMAIL.getValue());
    }
    if (userRepository.existsByPhone(userDto.getPhone())) {
      errors.reject(PHONE.name(), PHONE.getValue());
    }
    if (validateFullName(((UserDto) target).getName())){
      errors.reject(NAME.name(), NAME.getValue());
    }
  }

  private boolean validateFullName(String name){
    return StringUtil.isEmpty(name) || (StringUtil.isNotEmpty(name) && Arrays.stream(name.split(" ")).count() < 2);
  }
}
