package org.udsu.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udsu.warehouse.repository.dto.ProductDTO;
import org.udsu.warehouse.service.StorageService;
import org.udsu.warehouse.repository.dto.StorageDTO;

import java.util.List;

@RestController
@RequestMapping("/storages")
public class StorageController {

    @Autowired
    private StorageService storageService;

    // Get all storages
    @GetMapping
    public ResponseEntity<List<StorageDTO>> getAll() {
        return new ResponseEntity<>(storageService.findAll(), HttpStatus.OK);
    }

    // Get a single storage by ID
    @GetMapping("/{id}")
    public ResponseEntity<StorageDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(storageService.findById(id), HttpStatus.OK);
    }

    // Create a new storage
    @PostMapping
    public ResponseEntity<StorageDTO> create(@RequestBody StorageDTO storageDto) {
        return new ResponseEntity<>(storageService.insert(storageDto), HttpStatus.CREATED);
    }

    // Update an existing storage
    @PutMapping("/{id}")
    public ResponseEntity<StorageDTO> update(@PathVariable Long id, @RequestBody StorageDTO storageDto) {
        storageDto.setId(id); // ensure the ID in the DTO is correct
        return new ResponseEntity<>(storageService.update(storageDto), HttpStatus.OK);
    }

    // Delete a storage
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        storageService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsInStorage(@PathVariable("id") Long id) {
        return new ResponseEntity<>(storageService.findProductsByStorageId(id), HttpStatus.OK);
    }

    @PostMapping("/{storageId}/products/{productId}")
    public ResponseEntity<Void> addProductToStorage(@PathVariable("storageId") Long storageId,
                                                    @PathVariable("productId") Long productId,
                                                    @RequestParam("quantity") int quantity) {
        storageService.addProductToStorage(storageId, productId, quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{storageId}/products/{productId}")
    public ResponseEntity<Void> removeProductFromStorage(@PathVariable("storageId") Long storageId,
                                                         @PathVariable("productId") Long productId) {
        storageService.removeProductFromStorage(storageId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
