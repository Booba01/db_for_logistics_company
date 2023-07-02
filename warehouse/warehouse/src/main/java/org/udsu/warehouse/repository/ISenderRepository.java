package org.udsu.warehouse.repository;

import org.springframework.dao.DataAccessException;
import org.udsu.warehouse.repository.dto.SenderDTO;

import java.util.List;

public interface ISenderRepository {
    // Create
    void insert(SenderDTO senderDto) throws DataAccessException;

    // Read
    SenderDTO findById(Long id) throws DataAccessException;
    List<SenderDTO> findAll() throws DataAccessException;

    // Update
    void update(SenderDTO senderDto) throws DataAccessException;

    // Delete
    void deleteById(Long id) throws DataAccessException;
}

