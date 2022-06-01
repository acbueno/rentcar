package br.com.acbueno.rentcar.builder;

import br.com.acbueno.rentcar.model.Car;
import br.com.acbueno.rentcar.model.Category;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CarBuilder {

  private Car car;

  public static CarBuilder oneCar() {
    CarBuilder builder = new CarBuilder();
    builder.car = new Car();
    builder.car.setModeName("Versa");
    builder.car.setBrandName("Nissan");
    Category category = CategoryBuilder.oneCategory().build();
    builder.car.setCategory(category);

    builder.car.setUnit(10);

    return builder;
  }

  public CarBuilder outOfStock() {
    car.setUnit(0);

    return this;
  }

  public Car build() {
    return car;
  }

}
