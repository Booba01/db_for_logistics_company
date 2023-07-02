package org.udsu.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.udsu.warehouse.repository.IStorageRepository;
import org.udsu.warehouse.repository.dto.ProductDTO;
import org.udsu.warehouse.repository.dto.StorageDTO;

import java.util.List;

@Service
public class StorageService {

    private final IStorageRepository storageRepository;

    @Autowired
    public StorageService(IStorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    // Create
    public StorageDTO insert(StorageDTO storageDto) {
        storageRepository.insert(storageDto);
        return storageDto;
    }

    // Read
    public StorageDTO findById(Long id) {
        return storageRepository.findById(id);
    }

    public List<StorageDTO> findAll() {
        return storageRepository.findAll();
    }

    // Update
    public StorageDTO update(StorageDTO storageDto) {
        storageRepository.update(storageDto);
        return storageDto;
    }

    // Delete
    public void deleteById(Long id) {
        storageRepository.deleteById(id);
    }

    public List<ProductDTO> findProductsByStorageId(Long id) {
        return storageRepository.findProductsByStorageId(id);
    }

    public void addProductToStorage(Long storageId, Long productId, int quantity) {
        storageRepository.addProductToStorage(storageId, productId, quantity);
    }

    public void removeProductFromStorage(Long storageId, Long productId) {
        storageRepository.removeProductFromStorage(storageId, productId);
    }
}
