package br.com.acbueno.rentcar;

import static org.junit.Assert.assertThat;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import br.com.acbueno.rentcar.builder.CarBuilder;
import br.com.acbueno.rentcar.builder.UserBuilder;
import br.com.acbueno.rentcar.dao.RentDao;
import br.com.acbueno.rentcar.exceptions.CarNotFoundException;
import br.com.acbueno.rentcar.exceptions.RentException;
import br.com.acbueno.rentcar.model.Car;
import br.com.acbueno.rentcar.model.Rent;
import br.com.acbueno.rentcar.model.User;
import br.com.acbueno.rentcar.service.DocumentServices;
import br.com.acbueno.rentcar.service.RentService;

public class RentServiceTest {

  @Rule
  public ErrorCollector error = new ErrorCollector();

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @InjectMocks
  private RentService service;

  @Mock
  private RentDao dao;

  @Mock
  private DocumentServices documentoServices;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void shouldSuccessfullyRentCar() {
    User user = UserBuilder.oneUser().build();
    List<Car> cars = Arrays.asList(CarBuilder.oneCar().build());
    Rent rent;

    try {
      rent = service.rentCar(user, cars, 4, 10d);
      error.checkThat(rent.getRentPrice(), CoreMatchers.is(CoreMatchers.equalTo(195d)));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test(expected = CarNotFoundException.class)
  public void notShouldSuccesFullRentCarNotUnit() throws Exception {
    User user = UserBuilder.oneUser().build();
    List<Car> cars = Arrays.asList(CarBuilder.oneCar().outOfStock().build());
    Rent rent = service.rentCar(user, cars,4,10d);
    error.checkThat(rent.getRentPrice(), CoreMatchers.is(CoreMatchers.equalTo(400d)));
  }

  @Test
  public void notShouldRentOutCar() throws RentException, CarNotFoundException {
    User user = UserBuilder.oneUser().build();
    exception.expect(RentException.class);
    service.rentCar(user, null,0,0d);
  }

  @Test
  public void notShouldRentOutUser() throws CarNotFoundException {
    List<Car> cars = Arrays.asList(CarBuilder.oneCar().build());
    try {
      service.rentCar(null, cars,0,0d);
      Assert.fail();
    } catch (RentException e) {
      assertThat(e.getMessage(), CoreMatchers.is("User is null"));
    }
  }

  @Test
  public void shouldBonus20Percent() {
    double applyBonus = service.applyBonus(100d, 20d);
    assertThat(applyBonus, CoreMatchers.is(20d));
  }

  @Test
  public void shouldOutBonus() {
    double applyBonus = service.applyBonus(100d, 0d);
    assertThat(applyBonus, CoreMatchers.is(0d));
  }

  @Test
  public void notShouldRentCarNotValidLicenseDriver() throws Exception {
    User user = UserBuilder.oneUser().build();
    List<Car> cars = Arrays.asList(CarBuilder.oneCar().build());

    Mockito.when(documentoServices.driverLicenseValid(user)).thenReturn(true);

    exception.expect(RentException.class);
    exception.expectMessage("Driver license not valid");

    service.rentCar(user, cars, 2, 10d);
  }

}
