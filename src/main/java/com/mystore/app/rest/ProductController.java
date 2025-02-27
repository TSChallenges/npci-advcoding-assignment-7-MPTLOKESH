package com.mystore.app.rest;

import com.mystore.app.entity.Product;
import com.mystore.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product p = productService.addProduct(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
        Product p = productService.getProduct(id);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
        Product p = productService.updateProduct(id, product);
        if (p != null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO: API to search products by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
    List<Product> products = productService.searchProductsByName(name);
    return new ResponseEntity<>(products, HttpStatus.OK);
}


    // TODO: API to filter products by category
    @GetMapping("/category")
    public ResponseEntity<List<Product>> filterProductsByCategory(@RequestParam String category) {
    List<Product> products = productService.filterProductsByCategory(category);
    return new ResponseEntity<>(products, HttpStatus.OK);
}

    // TODO: API to filter products by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> filterProductsByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
    List<Product> products = productService.filterProductsByPriceRange(minPrice, maxPrice);
    return new ResponseEntity<>(products, HttpStatus.OK);
}


    // TODO: API to filter products by stock quantity range
    @GetMapping("/filter/stock")
    public ResponseEntity<List<Product>> filterProductsByStockRange(@RequestParam int minStock, @RequestParam int maxStock) {
    List<Product> products = productService.filterProductsByStockRange(minStock, maxStock);
    return new ResponseEntity<>(products, HttpStatus.OK);
}

}
