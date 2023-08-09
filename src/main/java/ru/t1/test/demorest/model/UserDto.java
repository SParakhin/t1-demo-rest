package ru.t1.test.demorest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
@Builder
public class UserDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Schema(description = "ФИО пользователя")
  @NotBlank(message = "Поле ФИО не должно быть пустым")
  private String name;

  @Pattern(regexp = "^79\\d*", message = "Телефонный номер должен начинаться с 79")
  @NotBlank(message = "Поле Телефон не должно быть пустым")
  @Schema(description = "Номер телефона")
  private String phone;

  //  @Schema(description = "Email")
  @Email(message = "Email должен быть корректным адресом электронной почты")
  @NotBlank(message = "Поле email не должно быть пустым")
  @Schema(description = "Email")
  private String email;

}
