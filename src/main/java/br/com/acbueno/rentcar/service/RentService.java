package br.com.acbueno.rentcar.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import br.com.acbueno.rentcar.dao.RentDao;
import br.com.acbueno.rentcar.exceptions.CarNotFoundException;
import br.com.acbueno.rentcar.exceptions.RentException;
import br.com.acbueno.rentcar.model.Car;
import br.com.acbueno.rentcar.model.Rent;
import br.com.acbueno.rentcar.model.User;
import br.com.acbueno.rentcar.service.util.DataUtil;
import lombok.val;

public class RentService {

  private RentDao dao;
  private DocumentServices documentoServices;

  public Rent rentCar(User user, List<Car> cars, int days, Double bonusPercent) throws RentException, CarNotFoundException {

    if (user == null) {
      throw new RentException("User is null");
    }

    if (cars == null || cars.isEmpty()) {
      throw new RentException("Cars is empty");
    }

    if(days == 0) {
      throw new RentException("Day is empty");
    }

    boolean notValidDriverLicense;

    try {
       notValidDriverLicense = documentoServices.driverLicenseValid(user);
    } catch (Exception e) {
      throw new RentException("Service not found");
    }

    if(notValidDriverLicense) {
      throw new RentException("Driver license not valid");
    }

    for (Car car : cars) {
      if (car.getUnit() == 0) {
        throw new CarNotFoundException("Not found car");
      }
    }

    Rent rent = new Rent();
    rent.setCars(cars);
    rent.setUser(user);
    rent.setRentDate(new Date());
    Double totalprice = 0d;

    for (int i = 0; i < cars.size(); i++) {
      Car car = cars.get(i);
      Double priceRent = car.getCategory().getRentPrice();

      totalprice += priceRent;
    }

    rent.setRentPrice(totalprice);

    Date rentEnd = new Date();
    rentEnd = DataUtil.addDays(rentEnd, days);

    rent.setReturnRentDate(rentEnd);

    long diffInMillies = Math.abs(rent.getRentDate().getTime() - rent.getReturnRentDate().getTime());
    long rentDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    Double total =  totalprice*rentDays;
    double applyBonus = applyBonus(totalprice, bonusPercent);
    rent.setRentPrice(total-applyBonus);


    dao.save(rent);

    return rent;

  }

  public double applyBonus(Double valueBonus, Double totalValue) {

    if(valueBonus == 0 && valueBonus == null) {
     return 0d;
    }
    return valueBonus/100*totalValue;
  }

}
