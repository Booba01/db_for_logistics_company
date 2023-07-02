package org.udsu.warehouse.repository;

import org.springframework.dao.DataAccessException;
import org.udsu.warehouse.repository.dto.RecipientDTO;

import java.util.List;

public interface IRecipientRepository {
    // Create
    void insert(RecipientDTO recipientDto) throws DataAccessException;

    // Read
    RecipientDTO findById(Long id) throws DataAccessException;
    List<RecipientDTO> findAll() throws DataAccessException;

    // Update
    void update(RecipientDTO recipientDto) throws DataAccessException;

    // Delete
    void deleteById(Long id) throws DataAccessException;
}