package org.udsu.warehouse.repository;

import org.springframework.dao.DataAccessException;
import org.udsu.warehouse.repository.dto.ShipmentDTO;
import org.udsu.warehouse.repository.dto.ProductDTO;

import java.util.List;

public interface IShipmentRepository {
    // Create
    void insert(ShipmentDTO shipmentDto) throws DataAccessException;

    // Read
    ShipmentDTO findById(Long id) throws DataAccessException;
    List<ShipmentDTO> findAll() throws DataAccessException;

    // Update
    void update(ShipmentDTO shipmentDto) throws DataAccessException;

    // Delete
    void deleteById(Long id) throws DataAccessException;

    List<ProductDTO> findProductsByShipmentId(Long shipmentId) throws DataAccessException;

    void addProductToShipment(Long shipmentId, Long productId) throws DataAccessException;

    void removeProductFromShipment(Long shipmentId, Long productId) throws DataAccessException;
}
