package com.veracruz.desafioAnotaai.controllers;

import com.veracruz.desafioAnotaai.domain.category.Category;
import com.veracruz.desafioAnotaai.domain.category.CategoryDTO;
import com.veracruz.desafioAnotaai.domain.category.exceptions.CategoryNotFoundException;
import com.veracruz.desafioAnotaai.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category")
public class CategoryController {
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable("id") String id) {
        Category category = this.service.getById(id)
            .orElseThrow(CategoryNotFoundException::new);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO categoryData) {
        Category newCategory = this.service.insert(categoryData);
        return ResponseEntity.ok().body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryData) {
        Category updatedCategory = this.service.update(id, categoryData);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id) {
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }
}
