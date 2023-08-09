package ru.t1.test.demorest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class UserUpdateRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @Schema(description = "ID пользователя")
  private UUID userId;

  @NotBlank(message = "Поле ФИО не должно быть пустым")
  @Schema(description = "ФИО пользователя")
  private String name;

  @Email(message = "Email должен быть корректным адресом электронной почты")
  @NotBlank(message = "Поле email не должно быть пустым")
  @Schema(description = "Email")
  private String email;

}
