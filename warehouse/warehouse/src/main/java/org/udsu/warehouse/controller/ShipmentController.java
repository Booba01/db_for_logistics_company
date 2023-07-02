// ShipmentController.java
package org.udsu.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udsu.warehouse.service.ShipmentService;
import org.udsu.warehouse.repository.dto.ShipmentDTO;
import org.udsu.warehouse.repository.dto.ProductDTO;

import java.util.List;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    @Autowired
    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<ShipmentDTO> createShipment(@RequestBody ShipmentDTO shipmentDTO) {
        return new ResponseEntity<>(shipmentService.insert(shipmentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentDTO> getShipment(@PathVariable Long id) {
        return new ResponseEntity<>(shipmentService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ShipmentDTO>> getAllShipments() {
        return new ResponseEntity<>(shipmentService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentDTO> updateShipment(@PathVariable Long id, @RequestBody ShipmentDTO shipmentDTO) {
        shipmentDTO.setId(id);
        return new ResponseEntity<>(shipmentService.update(shipmentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByShipmentId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(shipmentService.findProductsByShipmentId(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/products/{productId}")
    public ResponseEntity<Void> addProductToShipment(@PathVariable("id") Long id, @PathVariable Long productId) {
        shipmentService.addProductToShipment(id, productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/products/{productId}")
    public ResponseEntity<Void> removeProductFromShipment(@PathVariable("id") Long id, @PathVariable Long productId) {
        shipmentService.removeProductFromShipment(id, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
