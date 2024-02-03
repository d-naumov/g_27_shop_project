package de.aittr.g_27_shop_project.exception_handling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class SecondTestException extends RuntimeException{

  public SecondTestException(String message) {
    super(message);
  }

}
