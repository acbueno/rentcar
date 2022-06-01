package br.com.acbueno.rentcar.service;

import br.com.acbueno.rentcar.model.User;

public interface DocumentoServices {

  public boolean driverLicenseValid(User user) throws Exception;

}
