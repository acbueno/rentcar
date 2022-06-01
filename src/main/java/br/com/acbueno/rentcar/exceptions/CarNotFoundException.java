package br.com.acbueno.rentcar.exceptions;

public class CarNotFoundException extends Exception {

  private static final long serialVersionUID = 7089075218036137830L;

  public CarNotFoundException(String message) {
   super(message);
  }

}
