package com.mvyv.march11webapp.controller;

import com.mvyv.march11webapp.domain.Product;
import com.mvyv.march11webapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.getAll());
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
    Optional<Product> optionalProduct = productService.getById(id);
    if (optionalProduct.isPresent()) {
      return ResponseEntity.ok(optionalProduct.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
    return ResponseEntity.ok(productService.save(product));
  }

  @PutMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    Optional<Product> optionalProduct = productService.getById(id);
    if (optionalProduct.isPresent()) {
      Product found = optionalProduct.get();
      merge(found, product);
      return ResponseEntity.ok(productService.save(found));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:4200")
  public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
    productService.delete(id);
    return ResponseEntity.noContent().build();
  }


  private void merge(Product dbProduct, Product update) {
    dbProduct.setProductName(update.getProductName());
    dbProduct.setProductType(update.getProductType());
    dbProduct.setImage(update.getImage());
    dbProduct.setDescription(update.getDescription());
    dbProduct.setProductDate(update.getProductDate());
    dbProduct.setProductModificationDate(update.getProductModificationDate());
    dbProduct.setPrice(update.getPrice());
  }
}
