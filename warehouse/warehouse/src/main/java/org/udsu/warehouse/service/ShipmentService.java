// ShipmentService.java
package org.udsu.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.udsu.warehouse.repository.IShipmentRepository;
import org.udsu.warehouse.repository.dto.ProductDTO;
import org.udsu.warehouse.repository.dto.ShipmentDTO;

import java.util.List;

@Service
public class ShipmentService {

    private final IShipmentRepository shipmentRepository;

    @Autowired
    public ShipmentService(IShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public ShipmentDTO insert(ShipmentDTO shipmentDTO) {
        shipmentRepository.insert(shipmentDTO);
        return shipmentDTO;
    }

    public ShipmentDTO findById(Long id) {
        return shipmentRepository.findById(id);
    }

    public List<ShipmentDTO> findAll() {
        return shipmentRepository.findAll();
    }

    public ShipmentDTO update(ShipmentDTO shipmentDTO) {
        shipmentRepository.update(shipmentDTO);
        return shipmentDTO;
    }

    public void deleteById(Long id) {
        shipmentRepository.deleteById(id);
    }

    public List<ProductDTO> findProductsByShipmentId(Long shipmentId) {
        return shipmentRepository.findProductsByShipmentId(shipmentId);
    }

    public void addProductToShipment(Long shipmentId, Long productId) {
        shipmentRepository.addProductToShipment(shipmentId, productId);
    }

    public void removeProductFromShipment(Long shipmentId, Long productId) {
        shipmentRepository.removeProductFromShipment(shipmentId, productId);
    }
}
