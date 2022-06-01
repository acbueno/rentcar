package br.com.acbueno.rentcar.builder;

import br.com.acbueno.rentcar.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserBuilder {

  private User user;

  public static UserBuilder oneUser() {
    UserBuilder builder = new UserBuilder();
    builder.user = new User();
    builder.user.setName("User 1");

    return builder;
  }

  public UserBuilder withName(String name) {
    user.setName(name);
    return this;
  }

  public User build() {
    return user;
  }



}
