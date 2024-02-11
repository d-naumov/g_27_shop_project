package de.aittr.g_27_shop_project.services.mapping;

import de.aittr.g_27_shop_project.domain.dto.CartDto;
import de.aittr.g_27_shop_project.domain.dto.CustomerDto;
import de.aittr.g_27_shop_project.domain.interfaces.Cart;
import de.aittr.g_27_shop_project.domain.interfaces.Customer;
import de.aittr.g_27_shop_project.domain.jdbc.CommonCustomer;
import de.aittr.g_27_shop_project.domain.jpa.JpaCustomer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMappingService {
  private  CartMappingService cartMappingService;

  public CustomerMappingService(CartMappingService cartMappingService) {
    this.cartMappingService = cartMappingService;
  }

  public CustomerDto mapEntityToDto(Customer customer) {
    int id = customer.getId();
    String name = customer.getName();
    int age = customer.getAge();
    String email = customer.getEmail();
    return new CustomerDto(id, name, age,email);
  }

  public JpaCustomer mapDtoToEntity(CustomerDto customer) {
    int id = customer.getId();
    String name = customer.getName();
    int age = customer.getAge();
    String email = customer.getEmail();
    return new JpaCustomer(id, name, age,email);
  }
  public CommonCustomer mapDtoToCommonCustomer(CustomerDto customer) {
    int id = customer.getId();
    String name = customer.getName();

    CartDto cartDto = customer.getCart();
    Cart cart = cartMappingService.mapDtoToEntity(cartDto);

    return new CommonCustomer(id, true, name, cart);
  }
}
