// SenderService.java
package org.udsu.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.udsu.warehouse.repository.ISenderRepository;
import org.udsu.warehouse.repository.dto.SenderDTO;

import java.util.List;

@Service
public class SenderService {

    private final ISenderRepository senderRepository;

    @Autowired
    public SenderService(ISenderRepository senderRepository) {
        this.senderRepository = senderRepository;
    }

    public SenderDTO insert(SenderDTO senderDTO) {
        senderRepository.insert(senderDTO);
        return senderDTO;
    }

    public SenderDTO findById(Long id) {
        return senderRepository.findById(id);
    }

    public List<SenderDTO> findAll() {
        return senderRepository.findAll();
    }

    public SenderDTO update(SenderDTO senderDTO) {
        senderRepository.update(senderDTO);
        return senderDTO;
    }

    public void deleteById(Long id) {
        senderRepository.deleteById(id);
    }
}
