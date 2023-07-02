package org.udsu.warehouse.repository;

import org.springframework.dao.DataAccessException;
import org.udsu.warehouse.repository.dto.DealerDTO;

import java.util.List;

public interface IDealerRepository {
    // Create
    void insert(DealerDTO dealerDto) throws DataAccessException;

    // Read
    DealerDTO findById(Long id) throws DataAccessException;
    List<DealerDTO> findAll() throws DataAccessException;

    // Update
    void update(DealerDTO dealerDto) throws DataAccessException;

    // Delete
    void deleteById(Long id) throws DataAccessException;
}
