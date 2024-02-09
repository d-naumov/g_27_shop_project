package de.aittr.g_27_shop_project.services.jpa;

import de.aittr.g_27_shop_project.domain.dto.ProductDto;

import de.aittr.g_27_shop_project.domain.jpa.JpaProduct;

import de.aittr.g_27_shop_project.domain.jpa.Task;
import de.aittr.g_27_shop_project.exception_handling.exceptions.FourthTestException;
import de.aittr.g_27_shop_project.exception_handling.exceptions.NoActiveProducts;
import de.aittr.g_27_shop_project.exception_handling.exceptions.NoIdException;
import de.aittr.g_27_shop_project.exception_handling.exceptions.NoNameException;

import de.aittr.g_27_shop_project.repositories.jpa.JpaProductRepository;
import de.aittr.g_27_shop_project.scheduling.ScheduleExecutor;
import de.aittr.g_27_shop_project.services.interfaces.ProductService;
import de.aittr.g_27_shop_project.services.mapping.ProductMappingService;

import jakarta.transaction.Transactional;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JpaProductService implements ProductService {

  private JpaProductRepository repository;
  private ProductMappingService mappingService;
  // private Logger logger = LogManager.getLogger(JpaProductService.class);
  // private Logger logger = LoggerFactory.getLogger(JpaProductService.class);
  private  Logger logger = LoggerFactory.getLogger(JpaProductService.class);

  public JpaProductService(JpaProductRepository repository, ProductMappingService mappingService) {
    this.repository = repository;
    this.mappingService = mappingService;
  }

//  @Override
//  public ProductDto save(ProductDto product) {
//    try {
//      JpaProduct entity = mappingService.mapDtoToEntity(product);
//      entity.setId(0);
//      entity = repository.save(entity);
//      return mappingService.mapEntityToDto(entity);
//    } catch (Exception e) {
//      throw new FourthTestException(e.getMessage());
//    }
//  }

  @Override
  public ProductDto save(ProductDto product) {
    try {
      JpaProduct entity = mappingService.mapDtoToEntity(product);
      entity.setId(0);
      entity = repository.save(entity);
      ProductDto lastAddedProduct = getLastAddedProduct();
      if (lastAddedProduct != null) {
        String description = "Последний добавленный в БД продукт - " + lastAddedProduct.getName();
        Task task = new Task(description);
       // ScheduleExecutor.scheduleTaskExecution(task);
        logger.info(description);
      }
      return mappingService.mapEntityToDto(entity);
    } catch (Exception e) {
      throw new FourthTestException(e.getMessage());
    }
  }

  public ProductDto getLastAddedProduct() {
    try {
      List<ProductDto> allProducts = getAllActiveProducts();
      if (!allProducts.isEmpty()) {
        return allProducts.get(allProducts.size() - 1);
      }
    } catch (Exception e) {
      logger.error("Ошибка при получении последнего добавленного товара", e);
    }
    return null;
  }

  @Override
  public List<ProductDto> getAllActiveProducts() {
    Task task = new Task("Method getAllActiveProducts called ");
   // ScheduleExecutor.scheduleTaskExecution(task);
    return repository.findAll()
        .stream()
        .filter(x -> x.isActive())
        .map(x -> mappingService.mapEntityToDto(x)).toList();
  }

  @Override
  public ProductDto getActiveProductById(int id) {

//    logger.log(Level.INFO,String.format("Запрошен продукт с ИД %d.",id));
//    logger.log(Level.WARN,String.format("Запрошен продукт с ИД %d.",id));
//    logger.log(Level.ERROR,String.format("Запрошен продукт с ИД %d.",id));

//    logger.info(String.format("Запрошен продукт с ИД %d.",id));
//    logger.warn(String.format("Запрошен продукт с ИД %d.",id));
//    logger.error(String.format("Запрошен продукт с ИД %d.",id));

    JpaProduct product = repository.findById(id).orElse(null);

    if (product != null && product.isActive()) {
      return mappingService.mapEntityToDto(product);
    }
    throw new NoIdException("Продукт с указанным ИД отсутствует в БД.");
  }

  @Override
  public void update(ProductDto product) {
    JpaProduct entity = mappingService.mapDtoToEntity(product);
    repository.save(entity);

  }

  @Override
  @Transactional
  public void deleteById(int id) {
    JpaProduct product = repository.findById(id).orElse(null);
    if (product != null && product.isActive()) {
      product.setActive(false);
    } else {
      throw new NoIdException("Продукт с указанным ИД отсутствует в БД.");
    }
  }

  @Override
  @Transactional
  public void deleteByName(String name) {
    JpaProduct product = repository.findByName(name).orElse(null);
    if (product != null) {
      product.setActive(false);
      // Логирование успешного завершения метода
      logger.info(String.format("Метод %s успешно завершен",name));
    } else {
      logger.error(String.format("Продукт с name=%s не найден", name));
      throw new NoNameException("Продукт с указанным названием отсутствует в БД.");
    }
  }

  @Override
  @Transactional
  public void restoreById(int id) {
    JpaProduct product = repository.findById(id).orElse(null);
    if (product != null) {
      product.setActive(true);
    } else {
      throw new NoIdException("Продукт с указанным ИД отсутствует в БД.");
    }
  }

  @Override
  public int getActiveProductsCount() {
    return repository.countAllByIsActiveIsTrue();

  }

  @Override
  public double getActiveProductsTotalPrice() {
    List<ProductDto> activeProducts = getAllActiveProducts();
    if (activeProducts.isEmpty()) {
      throw new NoActiveProducts("Не удалось получить активные продукты");
    }
    return activeProducts.stream().mapToDouble(product -> product.getPrice()).sum();

  }

  @Override
  public double getActiveProductsAveragePrice() {

    List<ProductDto> activeProducts = getAllActiveProducts();
    if (activeProducts.isEmpty()) {
      throw new NoActiveProducts("Не удалось получить активные продукты");
    }
    return activeProducts.stream().mapToDouble(ProductDto::getPrice).average().getAsDouble();
  }

}
