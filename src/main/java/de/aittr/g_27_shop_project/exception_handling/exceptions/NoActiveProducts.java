package de.aittr.g_27_shop_project.exception_handling.exceptions;

public class NoActiveProducts extends RuntimeException{

  public NoActiveProducts(String message) {
    super(message);
  }
}
