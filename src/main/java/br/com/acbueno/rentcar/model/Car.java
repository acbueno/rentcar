package br.com.acbueno.rentcar.model;


import br.com.acbueno.rentcar.annotions.Generated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Generated
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Car {

  private String modeName;
  private String brandName;
  private Category category;
  private int unit;

}
