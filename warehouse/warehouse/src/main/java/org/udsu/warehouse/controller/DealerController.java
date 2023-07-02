package org.udsu.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udsu.warehouse.service.DealerService;
import org.udsu.warehouse.repository.dto.DealerDTO;

import java.util.List;

@RestController
@RequestMapping("/dealers")
public class DealerController {

    private final DealerService dealerService;

    @Autowired
    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @GetMapping
    public ResponseEntity<List<DealerDTO>> getAllDealers() {
        return ResponseEntity.ok(dealerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealerDTO> getDealerById(@PathVariable Long id) {
        return ResponseEntity.ok(dealerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<DealerDTO> createDealer(@RequestBody DealerDTO dealerDto) {
        return ResponseEntity.ok(dealerService.insert(dealerDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealerDTO> updateDealer(@PathVariable Long id, @RequestBody DealerDTO dealerDto) {
        if (!id.equals(dealerDto.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(dealerService.update(dealerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealer(@PathVariable Long id) {
        dealerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
