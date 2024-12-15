package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.request.ProductDTO;
import com.example.demo.response.ProductResponseDTO;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDTO> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public Product save(@RequestBody ProductDTO product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("/search")
    public List<Product> findByCategoryNameLike(@RequestParam String name) {
        return productService.findByCategoryNameLike(name);
    }
}
