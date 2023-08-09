package ru.t1.test.demorest.validate;

import lombok.Getter;

@Getter
public enum ErrorCode {

  NAME("В поле ФИО необходимо указать имя и фамилию через пробел"),
  PHONE("Пользователь с таким телефоном уже существует"),
  EMAIL("Пользователь с таким email уже существует"),

  USER_NOT_FOUND("Пользователь с указанным id не найден");

  private final String value;

  ErrorCode(String value) {
    this.value = value;
  }
}
