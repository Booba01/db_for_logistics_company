// RecipientService.java
package org.udsu.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.udsu.warehouse.repository.IRecipientRepository;
import org.udsu.warehouse.repository.dto.RecipientDTO;

import java.util.List;

@Service
public class RecipientService {

    private final IRecipientRepository recipientRepository;

    @Autowired
    public RecipientService(IRecipientRepository recipientRepository) {
        this.recipientRepository = recipientRepository;
    }

    public RecipientDTO insert(RecipientDTO recipientDTO) {
        recipientRepository.insert(recipientDTO);
        return recipientDTO;
    }

    public RecipientDTO findById(Long id) {
        return recipientRepository.findById(id);
    }

    public List<RecipientDTO> findAll() {
        return recipientRepository.findAll();
    }

    public RecipientDTO update(RecipientDTO recipientDTO) {
        recipientRepository.update(recipientDTO);
        return recipientDTO;
    }

    public void deleteById(Long id) {
        recipientRepository.deleteById(id);
    }
}
