package org.udsu.warehouse.repository;

import java.util.List;
import org.springframework.dao.DataAccessException;

import org.udsu.warehouse.repository.dto.ProductDTO;

public interface IProductRepository {
    // Create
    void insert(ProductDTO productDTO) throws DataAccessException;

    // Read
    ProductDTO findById(Long id) throws DataAccessException;
    List<ProductDTO> findAll() throws DataAccessException;
    List<ProductDTO> findByDealerId(Long dealerId) throws DataAccessException;

    // Update
    void update(ProductDTO productDTO) throws DataAccessException;

    // Delete
    void deleteById(Long id) throws DataAccessException;
}

