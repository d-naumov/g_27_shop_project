package de.aittr.g_27_shop_project.domain.dto;

import de.aittr.g_27_shop_project.domain.interfaces.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartDto {
  private int id;
  private List<ProductDto> products = new ArrayList<>();

  public CartDto(int id, List<ProductDto> products) {
    this.id = id;
    this.products = products;
  }


  public int getId() {
    return id;
  }

  public List<ProductDto> getProducts() {
    return products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CartDto cartDto = (CartDto) o;

    if (id != cartDto.id) {
      return false;
    }
    return Objects.equals(products, cartDto.products);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (products != null ? products.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "CartDto{" +
        "id=" + id +
        ", products=" + products +
        '}';
  }
}
