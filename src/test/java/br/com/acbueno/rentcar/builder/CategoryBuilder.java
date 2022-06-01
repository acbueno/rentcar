package br.com.acbueno.rentcar.builder;

import br.com.acbueno.rentcar.model.Category;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryBuilder {

  private Category category;

  public static CategoryBuilder oneCategory() {
    CategoryBuilder builder = new CategoryBuilder();
    builder.category = new Category();
    builder.category.setCategory("Economy Sedans");
    builder.category.setDescription("Economy Sedans");
    builder.category.setRentPrice(50d);

    return builder;
  }

  public Category build() {
    return category;
  }

}
