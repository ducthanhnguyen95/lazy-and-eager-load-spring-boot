package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.ProductDTO;
import com.example.demo.response.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll().stream().map(product -> {
            ProductResponseDTO dto = new ProductResponseDTO();
            dto.setId(product.getId());
            dto.setName(product.getName());
            dto.setPrice(product.getPrice());
            dto.setCategoryName(product.getCategory().getName());
            return dto;
        }).collect(Collectors.toList());
    }

    public Product save(ProductDTO productDTO) {
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            Category category = product.getCategory();
            product.setCategory(category);// Trigger lazy loading
        }

        return product;
    }

    public List<Product> findByCategoryNameLike(String name) {
        List<Product> products = productRepository.findByCategoryNameLike(name);
        return products;
    }
}
