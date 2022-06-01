package br.com.acbueno.rentcar.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Car {

  private String modeName;
  private String brandName;
  private Category category;
  private int unit;

}
