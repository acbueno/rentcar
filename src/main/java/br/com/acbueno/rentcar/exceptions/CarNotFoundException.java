package br.com.acbueno.rentcar.exceptions;

import br.com.acbueno.rentcar.annotions.Generated;

@Generated
public class CarNotFoundException extends Exception {

  private static final long serialVersionUID = 7089075218036137830L;

  public CarNotFoundException(String message) {
   super(message);
  }

}
