// DealerService.java
package org.udsu.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.udsu.warehouse.repository.IDealerRepository;
import org.udsu.warehouse.repository.dto.DealerDTO;

import java.util.List;

@Service
public class DealerService {

    private final IDealerRepository dealerRepository;

    @Autowired
    public DealerService(IDealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    public DealerDTO insert(DealerDTO dealerDto) {
        dealerRepository.insert(dealerDto);
        return dealerDto;
    }

    public DealerDTO findById(Long id) {
        return dealerRepository.findById(id);
    }

    public List<DealerDTO> findAll() {
        return dealerRepository.findAll();
    }

    public DealerDTO update(DealerDTO dealerDto) {
        dealerRepository.update(dealerDto);
        return dealerDto;
    }

    public void deleteById(Long id) {
        dealerRepository.deleteById(id);
    }
}
