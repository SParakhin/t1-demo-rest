package ru.t1.test.demorest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSearchResult implements Serializable {

  private static final long serialVersionUID = 1L;

  @Schema(description = "Id пользователя")
  private UUID userId;

  @Schema(description = "Номер телефона")
  private String phone;

  @Schema(description = "ФИО клиента")
  private String name;

  @Schema(description = "Email")
  private String email;

  @Schema(description = "Код ошибки")
  private String errorCode;

}
