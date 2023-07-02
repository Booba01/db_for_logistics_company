// SenderController.java
package org.udsu.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udsu.warehouse.service.SenderService;
import org.udsu.warehouse.repository.dto.SenderDTO;

import java.util.List;

@RestController
@RequestMapping("/senders")
public class SenderController {

    private final SenderService senderService;

    @Autowired
    public SenderController(SenderService senderService) {
        this.senderService = senderService;
    }

    @PostMapping
    public ResponseEntity<SenderDTO> createSender(@RequestBody SenderDTO senderDTO) {
        return new ResponseEntity<>(senderService.insert(senderDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SenderDTO> getSender(@PathVariable Long id) {
        return new ResponseEntity<>(senderService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SenderDTO>> getAllSenders() {
        return new ResponseEntity<>(senderService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SenderDTO> updateSender(@PathVariable Long id, @RequestBody SenderDTO senderDTO) {
        senderDTO.setId(id);
        return new ResponseEntity<>(senderService.update(senderDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSender(@PathVariable Long id) {
        senderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
