package de.aittr.g_27_shop_project.exception_handling;

import de.aittr.g_27_shop_project.exception_handling.exceptions.FourthTestException;
import de.aittr.g_27_shop_project.exception_handling.exceptions.NoActiveProducts;
import de.aittr.g_27_shop_project.exception_handling.exceptions.NoIdException;
import de.aittr.g_27_shop_project.exception_handling.exceptions.NoNameException;
import de.aittr.g_27_shop_project.exception_handling.exceptions.ThirdTestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonAdvice {

  @ExceptionHandler(ThirdTestException.class)
  public ResponseEntity<Response> handleException(ThirdTestException e) {
    Response response = new Response(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
  }

  @ExceptionHandler(FourthTestException.class)
  public ResponseEntity<Response> handleException(FourthTestException e) {
    Response response = new Response(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoIdException.class)
  public ResponseEntity<Response> handleException(NoIdException e) {
    Response response = new Response(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
  }

  @ExceptionHandler(NoNameException.class)
  public ResponseEntity<Response> handleException(NoNameException e) {
    Response response = new Response(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoActiveProducts.class)
  public ResponseEntity<Response> handleException(NoActiveProducts e) {
    Response response = new Response(e.getMessage());
    return new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT);
  }

}
