package de.aittr.g_27_shop_project.domain.jpa;

import de.aittr.g_27_shop_project.domain.interfaces.Cart;
import de.aittr.g_27_shop_project.domain.interfaces.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "customer")
public class JpaCustomer implements Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
  @Column(name = "name")
  @Pattern(regexp = "[A-Z][a-z]{3,}")
  private String name;
  @Column(name = "age")
  @Min(18)
  @Max(90)
  private int age;
  @Column(name = "email")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
  private String email;
  @Column(name = "is_active")
  private boolean isActive;

  @OneToOne(mappedBy = "customer")
  private JpaCart cart;
  private static Logger logger = LoggerFactory.getLogger(JpaCustomer.class);

  public JpaCustomer(int id, String name, int age, String email) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.email = email;
    this.isActive = true;
    logger.info(String.format(
        "Конструктор JpaCustomer вызван с параметрами id=%d, name=%s, age=%d, email=%s",
        id, name, age, email));
  }


  public int getAge() {
    logger.info("Метод getAge вызван");
    return age;
  }

  public void setAge(int age) {
    logger.info(String.format("Метод setAge вызван с параметром price=%d", age));
    this.age = age;
  }


  public String getEmail() {
    logger.info("Метод getEmail вызван");
    return email;
  }

  public void setEmail(String email) {
    logger.info(String.format("Метод setPrice вызван с параметром price=%s", email));
    this.email = email;
  }

  @Override
  public int getId() {
    logger.info("Метод getId вызван");
    return id;
  }

  @Override
  public void setId(int id) {
    logger.info(String.format("Метод setPrice вызван с параметром price=%d", id));
    this.id = id;
  }

  @Override
  public String getName() {
    logger.info("Метод getName вызван");
    return name;
  }

  @Override
  public void setName(String name) {
    logger.info(String.format("Метод setPrice вызван с параметром price=%s", name));
    this.name = name;
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
  public Cart getCart() {
    logger.info("Метод getCart вызван");
    return cart;
  }

  @Override
  public void setCart(Cart cart) {
    logger.info(String.format("Метод setCart вызван с параметром price=%s", cart));
    JpaCart entity = new JpaCart();
    entity.setId(cart.getId());
    entity.setProducts(cart.getProducts());
    this.cart = entity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    JpaCustomer that = (JpaCustomer) o;

    if (id != that.id) {
      return false;
    }
    if (isActive != that.isActive) {
      return false;
    }
    if (!Objects.equals(name, that.name)) {
      return false;
    }
    return Objects.equals(cart, that.cart);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (isActive ? 1 : 0);
    result = 31 * result + (cart != null ? cart.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    logger.info(String.format(
        "Метод toString вызван. Состояние объекта: id=%d, name=%s, age=%d, email=%s, isActive=%b",
        id, name, age, isActive));
    return "JpaCustomer{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", age=" + age +
        ", email='" + email + '\'' +
        ", isActive=" + isActive +
        ", cart=" + cart +
        '}';
  }
}
