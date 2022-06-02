package br.com.acbueno.rentcar.service;

import br.com.acbueno.rentcar.annotions.Generated;
import br.com.acbueno.rentcar.model.User;

@Generated
public interface DocumentServices {

  public boolean driverLicenseValid(User user) throws Exception;

}
