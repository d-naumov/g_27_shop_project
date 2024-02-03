package de.aittr.g_27_shop_project.domain.jpa;

import de.aittr.g_27_shop_project.domain.interfaces.Cart;
import de.aittr.g_27_shop_project.domain.interfaces.Customer;
import de.aittr.g_27_shop_project.domain.interfaces.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "cart")
public class JpaCart implements Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @OneToOne
  @JoinColumn(name = "customer_id")
  private JpaCustomer customer;
  @ManyToMany
  @JoinTable(
      name = "card_product",
      joinColumns = @JoinColumn(name = "cart_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  private List<JpaProduct> products;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }

  public JpaCustomer getCustomer() {
    return customer;
  }

  public void setCustomer(JpaCustomer customer) {
    this.customer = customer;
  }

//  public Customer getCustomer() {
//    return customer;
//  }
//
//  public void setCustomer(JpaCustomer customer) {
//    JpaCustomer entity = new JpaCustomer();
//    entity.setId(customer.getId());
//    entity.setName(customer.getName());
//    entity.setActive(customer.isActive());
//    entity.setCart(customer.getCart());
//    this.customer = entity;
//  }

  @Override
  public List<Product> getProducts() {
    return new ArrayList<>(products);
  }
  private Logger logger = LoggerFactory.getLogger(JpaCart.class);

  @Override
  public void setProducts(List<Product> products) {
    logger.info(String.format("Метод setProducts вызван. Устанавливаем продукты: ", products));
    this.products = products.stream().map(x -> {
      JpaProduct entity = new JpaProduct();
      entity.setId(x.getId());
      entity.setName(x.getName());
      entity.setActive(x.isActive());
      entity.setPrice(x.getPrice());
      return entity;
    }).toList();
  }

  @Override
  public void addProduct(Product product) {
    logger.info(String.format("Метод addProduct вызван. Добавляем продукт: %s " , product));
  }

  @Override
  public void deleteProductById(int Id) {
    logger.info(String.format("Метод deleteProductById вызван. Удаляем продукт по Id: %d " , id));


    logger.info(String.format("Продукт с Id %d успешно удален из корзины.", id));
    }


  @Override
  public void clear() {
    logger.info("Метод clear вызван. Очищаем корзину.");

  }

  @Override
  public double getTotalPrice() {
    logger.info("Метод getTotalPrice вызван. Получаем общую стоимость продуктов.");
    return 0;
  }

  @Override
  public double getAveragePrice() {
    logger.info("Метод getAveragePrice вызван. Получаем среднюю стоимость продуктов.");
    return 0;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    JpaCart jpaCart = (JpaCart) o;

    if (id != jpaCart.id) {
      return false;
    }
    if (!Objects.equals(customer, jpaCart.customer)) {
      return false;
    }
    return Objects.equals(products, jpaCart.products);
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (customer != null ? customer.hashCode() : 0);
    result = 31 * result + (products != null ? products.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    logger.info("Метод toString вызван.");
    logger.info(String.format("Состояние объекта: id=%d, customer=%s, products=%s", id, customer, products));
    return "JpaCart{" +
        "id=" + id +
        ", customer=" + customer +
        ", products=" + products +
        '}';
  }
}
