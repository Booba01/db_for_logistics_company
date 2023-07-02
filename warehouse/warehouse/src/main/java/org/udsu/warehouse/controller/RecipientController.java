// RecipientController.java
package org.udsu.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.udsu.warehouse.service.RecipientService;
import org.udsu.warehouse.repository.dto.RecipientDTO;

import java.util.List;

@RestController
@RequestMapping("/recipients")
public class RecipientController {

    private final RecipientService recipientService;

    @Autowired
    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }

    @PostMapping
    public ResponseEntity<RecipientDTO> createRecipient(@RequestBody RecipientDTO recipientDTO) {
        return new ResponseEntity<>(recipientService.insert(recipientDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipientDTO> getRecipient(@PathVariable Long id) {
        return new ResponseEntity<>(recipientService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecipientDTO>> getAllRecipients() {
        return new ResponseEntity<>(recipientService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipientDTO> updateRecipient(@PathVariable Long id, @RequestBody RecipientDTO recipientDTO) {
        recipientDTO.setId(id);
        return new ResponseEntity<>(recipientService.update(recipientDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipient(@PathVariable Long id) {
        recipientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
