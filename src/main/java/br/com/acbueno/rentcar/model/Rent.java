package br.com.acbueno.rentcar.model;

import java.util.Date;
import java.util.List;

import br.com.acbueno.rentcar.annotions.Generated;
import lombok.Data;

@Generated
@Data
public class Rent {

  private User user;
  private List<Car> cars;
  private Date rentDate;
  private Date returnRentDate;
  private Double rentPrice;

}
