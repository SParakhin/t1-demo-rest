package ru.t1.test.demorest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.test.demorest.model.UserDto;
import ru.t1.test.demorest.model.UserSearchResult;
import ru.t1.test.demorest.model.UserUpdateRequest;
import ru.t1.test.demorest.service.UserService;
import ru.t1.test.demorest.validate.UniqueValidator;

@Slf4j
@RequiredArgsConstructor
@RestController
public class Controller {

  private final UserService userService;

  private final UniqueValidator validator;

  @PostMapping(value = "/api/user/createUser", produces = "application/json", consumes = "application/json")
  @Operation(summary = "Создать запись о пользователе")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос обработан"),
      @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
      @ApiResponse(responseCode = "500", description = "Ошибка обработки запроса")})
  ResponseEntity<String> createUser(@Valid @RequestBody UserDto request, BindingResult result) {
    validator.validate(request, result);
    if (result.hasErrors()) {
      return ResponseEntity.badRequest().body(getBody(result));
    }
    return ResponseEntity.ok(userService.createUser(request));
  }

  @PutMapping(value = "/api/user/updateUser", produces = "application/json", consumes = "application/json")
  @Operation(summary = "Изменить запись о пользователе")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос обработан"),
      @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
      @ApiResponse(responseCode = "500", description = "Ошибка обработки запроса")})
  ResponseEntity<String> updateUser(@Valid @RequestBody UserUpdateRequest request, BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.badRequest().body(getBody(result));
    }
    return ResponseEntity.ok(userService.updateUser(request));
  }

  @GetMapping(value = "/api/user/getUser", produces = "application/json", consumes = "application/json")
  @Operation(summary = "Найти запись о пользователе")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос обработан"),
      @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
      @ApiResponse(responseCode = "500", description = "Ошибка обработки запроса")})
  ResponseEntity<UserSearchResult> getUser(@RequestParam UUID userId, @RequestParam String phone) {
    return ResponseEntity.ok(userService.getUser(userId, phone));
  }

  @DeleteMapping(value = "/api/user/deleteUser")
  @Operation(summary = "Удалить запись о пользователе")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос обработан"),
      @ApiResponse(responseCode = "400", description = "Ошибка в запросе"),
      @ApiResponse(responseCode = "500", description = "Ошибка обработки запроса")})
  void getUser(@RequestParam UUID userId) {
    userService.removeUser(userId);
  }

  private String getBody(BindingResult result) {
    return result.getAllErrors().stream().map(
        DefaultMessageSourceResolvable::getDefaultMessage).collect(
        Collectors.joining(";" + "\n"));
  }

}
