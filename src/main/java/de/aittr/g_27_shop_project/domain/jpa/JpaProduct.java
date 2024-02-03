package de.aittr.g_27_shop_project.domain.jpa;

import de.aittr.g_27_shop_project.domain.interfaces.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "product")
public class JpaProduct implements Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
 // @NotNull
 // @NotBlank
  @Pattern(regexp = "[A-Z][a-z]{3,}")
  private String name;
  @Column(name = "price")
  @Max(90000)
  @Min(10)
  private double price;
  @Column(name = "is_active")
  private boolean isActive;
  private Logger logger = LoggerFactory.getLogger(JpaProduct.class);

  public JpaProduct(int id, String name, double price, boolean isActive) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.isActive = isActive;
    logger.info(String.format("Конструктор JpaProduct вызван с параметрами id=%d, name=%s, price=%f, isActive=%b",
        id, name, price, isActive));
  }

  public JpaProduct() {
    logger.info("Вызван конструктор JpaProduct по умолчанию");
  }

  @Override
  public int getId() {
    logger.info("Метод getId вызван");
    return id;

  }

  @Override
  public void setId(int id) {
    this.id = id;
    logger.info(String.format("Метод setId вызван с параметром id=%d", id));
  }

  @Override
  public String getName() {
    logger.info("Метод getName вызван");
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
    logger.info(String.format("Метод setName вызван с параметром name=%s", name));
  }

  @Override
  public double getPrice() {
    logger.info("Метод getPrice вызван");
    return price;
  }

  @Override
  public void setPrice(double price) {
    logger.info(String.format("Метод setPrice вызван с параметром price=%f", price));
    this.price = price;
  }

  @Override
  public boolean isActive() {
    logger.info("Метод isActive вызван");
    return isActive;
  }

  @Override
  public void setActive(boolean active) {
    logger.info(String.format("Метод setActive вызван с параметром active=%b", active));
    isActive = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    JpaProduct that = (JpaProduct) o;

    if (id != that.id) {
      return false;
    }
    if (Double.compare(price, that.price) != 0) {
      return false;
    }
    if (isActive != that.isActive) {
      return false;
    }
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    temp = Double.doubleToLongBits(price);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + (isActive ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    logger.info(String.format("Метод toString вызван. Состояние объекта: id=%d, name=%s, price=%f, isActive=%b",
        id, name, price, isActive));
    return "JpaProduct{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", price=" + price +
        ", isActive=" + isActive +
        '}';
  }
}
