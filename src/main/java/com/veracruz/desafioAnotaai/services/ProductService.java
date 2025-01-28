package com.veracruz.desafioAnotaai.services;

import com.veracruz.desafioAnotaai.domain.category.exceptions.CategoryNotFoundException;
import com.veracruz.desafioAnotaai.domain.product.Product;
import com.veracruz.desafioAnotaai.domain.product.ProductDTO;
import com.veracruz.desafioAnotaai.domain.product.exceptions.ProductNotFoundException;
import com.veracruz.desafioAnotaai.repositories.ProductRepository;
import com.veracruz.desafioAnotaai.services.aws.AwsSnsService;
import com.veracruz.desafioAnotaai.services.aws.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final AwsSnsService snsService;

    public List<Product> getAll() {
        return this.repository.findAll();
    }

    public Optional<Product> getById(String id) {
        return this.repository.findById(id);
    }

    public Product insert(ProductDTO productData) {
        this.categoryService.getById(productData.categoryId());

        Product newProduct = new Product(productData);
        this.repository.save(newProduct);

        this.snsService.publish(new MessageDTO(newProduct.getOwnerId()));

        return newProduct;
    }

    public Product update(String id, ProductDTO productData) {
        Product updatedProduct = this.repository.findById(id)
            .orElseThrow(ProductNotFoundException::new);

        if (!productData.categoryId().isEmpty()) {
            this.categoryService.getById(productData.categoryId())
                .orElseThrow(CategoryNotFoundException::new);

            updatedProduct.setCategoryId(productData.categoryId());
        }

        if (!productData.title().isEmpty()) updatedProduct.setTitle(productData.title());
        if (!productData.description().isEmpty()) updatedProduct.setDescription(productData.description());
        if (!(productData.price() == null)) updatedProduct.setPrice(productData.price());

        this.repository.save(updatedProduct);

        this.snsService.publish(new MessageDTO(updatedProduct.getOwnerId()));

        return updatedProduct;
    }

    public void delete(String id) {
        Product product = this.getById(id)
            .orElseThrow(ProductNotFoundException::new);

        this.repository.delete(product);
    }
}
