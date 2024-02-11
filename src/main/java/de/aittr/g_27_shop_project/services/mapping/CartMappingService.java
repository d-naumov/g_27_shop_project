package de.aittr.g_27_shop_project.services.mapping;

import de.aittr.g_27_shop_project.domain.dto.CartDto;
import de.aittr.g_27_shop_project.domain.dto.ProductDto;
import de.aittr.g_27_shop_project.domain.interfaces.Cart;
import de.aittr.g_27_shop_project.domain.interfaces.Product;
import de.aittr.g_27_shop_project.domain.jdbc.CommonCart;
import de.aittr.g_27_shop_project.domain.jdbc.CommonProduct;
import de.aittr.g_27_shop_project.domain.jpa.JpaCart;
import de.aittr.g_27_shop_project.domain.jpa.JpaProduct;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CartMappingService {

  public CartDto mapEntityToDto(Cart cart) {
    int id = cart.getId();
    List<Product> products = cart.getProducts();

    // Маппинг списка продуктов в список DTO
    List<ProductDto> productDto = products.stream()
        .map(product -> new ProductDto(product.getId(), product.getName(), product.getPrice()))
        .collect(Collectors.toList());

    return new CartDto(id, productDto);
  }

  public JpaCart mapDtoToEntity(CartDto cart) {
    int id = cart.getId();

    List<Product> products = cart.getProducts().stream()
        .map(productDto -> new JpaProduct(productDto.getId(), productDto.getName(),
            productDto.getPrice(), true))
        .collect(Collectors.toList());

    JpaCart jpaCart = new JpaCart();
    jpaCart.setId(id);
    jpaCart.setProducts(products);

    return jpaCart;
   // return new JpaCart(id, products);
  }

  public CommonCart mapDtoToCommonCart(CartDto cart) {
    int id = cart.getId();

    // Маппинг списка DTO в список продуктов
    List<Product> products = cart.getProducts().stream()
        .map(productDto -> new CommonProduct(productDto.getId(), productDto.getName(),
            productDto.getPrice()))
        .collect(Collectors.toList());

    CommonCart commonCart = new CommonCart();
    commonCart.setId(id);
    commonCart.setProducts(products);

    return commonCart;


  }


}
