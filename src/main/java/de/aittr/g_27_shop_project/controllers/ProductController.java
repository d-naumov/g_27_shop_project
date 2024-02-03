package de.aittr.g_27_shop_project.controllers;

import de.aittr.g_27_shop_project.domain.dto.ProductDto;
import de.aittr.g_27_shop_project.domain.jdbc.CommonProduct;
import de.aittr.g_27_shop_project.domain.interfaces.Product;
import de.aittr.g_27_shop_project.exception_handling.Response;
import de.aittr.g_27_shop_project.exception_handling.exceptions.FirstTestException;
import de.aittr.g_27_shop_project.services.interfaces.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

  private ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  @PostMapping
  public ProductDto save(@Valid @RequestBody ProductDto product) {
    return service.save(product);
  }

  @GetMapping
  public List<ProductDto> getAll() {
    return service.getAllActiveProducts();
  }

  @GetMapping("/{id}")
  public ProductDto getById(@PathVariable int id) {
    return service.getActiveProductById(id);

  }

  @PutMapping
  public void update(@RequestBody ProductDto product) {
    service.update(product);
  }

  @GetMapping("/restore/{id}")
  public void restoreById(@PathVariable int id) {
    service.restoreById(id);
  }

  @DeleteMapping("/deleteById/{id}")
  public void deleteById(@PathVariable int id) {
    service.deleteById(id);
  }

  @DeleteMapping("/deleteByName/{name}")
  public void deleteByName(@PathVariable String name) {
    service.deleteByName(name);
  }

  @GetMapping("/total")
  public double getActiveProductsTotalPrice() {
    return service.getActiveProductsTotalPrice();
  }

  @GetMapping("/average")

  public double getActiveProductsAveragePrice() {
    return service.getActiveProductsAveragePrice();
  }

  @GetMapping("/count")
  public int getActiveProductsCount() {
    return service.getActiveProductsCount();
  }

  @ExceptionHandler(FirstTestException.class)
  @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
  public Response handleException(FirstTestException e) {
    return new Response(e.getMessage());
  }
}
