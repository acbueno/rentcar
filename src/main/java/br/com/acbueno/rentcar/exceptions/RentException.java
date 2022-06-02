package br.com.acbueno.rentcar.exceptions;

import br.com.acbueno.rentcar.annotions.Generated;

@Generated
public class RentException extends Exception {

  private static final long serialVersionUID = 8720608735591607036L;

  public RentException(String message) {
    super(message);
  }

}
