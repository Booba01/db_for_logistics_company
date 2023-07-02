package org.udsu.warehouse.repository;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.udsu.warehouse.repository.dto.ProductDTO;
import org.udsu.warehouse.repository.dto.StorageDTO;

public interface IStorageRepository {
    // Create
    void insert(StorageDTO storageDto) throws DataAccessException;

    // Read
    StorageDTO findById(Long id) throws DataAccessException;
    List<StorageDTO> findAll() throws DataAccessException;

    // Update
    void update(StorageDTO storageDto) throws DataAccessException;

    // Delete
    void deleteById(Long id) throws DataAccessException;

    List<ProductDTO> findProductsByStorageId(Long id) throws DataAccessException;

    void addProductToStorage(Long storageId, Long productId, int quantity) throws DataAccessException;

    void removeProductFromStorage(Long storageId, Long productId) throws DataAccessException;

}
