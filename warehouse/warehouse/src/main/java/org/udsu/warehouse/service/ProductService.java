// ProductService.java
package org.udsu.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.udsu.warehouse.repository.IProductRepository;
import org.udsu.warehouse.repository.dto.ProductDTO;

import java.util.List;

@Service
public class ProductService {

    private final IProductRepository productRepository;

    @Autowired
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDTO insert(ProductDTO productDTO) {
        productRepository.insert(productDTO);
        return productDTO;
    }

    public ProductDTO findById(Long id) {
        return productRepository.findById(id);
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll();
    }

    public ProductDTO update(ProductDTO productDTO) {
        productRepository.update(productDTO);
        return productDTO;
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
