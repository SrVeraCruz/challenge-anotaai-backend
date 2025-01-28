package com.veracruz.desafioAnotaai.controllers;

import com.veracruz.desafioAnotaai.domain.product.Product;
import com.veracruz.desafioAnotaai.domain.product.ProductDTO;
import com.veracruz.desafioAnotaai.domain.product.exceptions.ProductNotFoundException;
import com.veracruz.desafioAnotaai.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = this.service.getAll();
        return ResponseEntity.ok().body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable("id") String id) {
        Product product = this.service.getById(id)
            .orElseThrow(ProductNotFoundException::new);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping()
    public ResponseEntity<Product> insert(@RequestBody ProductDTO productData) {
        Product newProduct = this.service.insert(productData);
        return ResponseEntity.ok().body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productData) {
        Product updatedProduct = this.service.update(id, productData);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
