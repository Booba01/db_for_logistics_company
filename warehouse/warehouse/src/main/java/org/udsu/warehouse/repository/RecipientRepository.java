package org.udsu.warehouse.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.udsu.warehouse.repository.dto.RecipientDTO;

@Repository
public class RecipientRepository extends BaseRepository implements IRecipientRepository {

    public RecipientRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void insert(RecipientDTO recipientDTO) throws DataAccessException {
        String sql = "INSERT INTO Recipient (Name, Email, Phone, Address) VALUES (:name, :email, :phone, :address)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", recipientDTO.getName());
        params.addValue("email", recipientDTO.getEmail());
        params.addValue("phone", recipientDTO.getPhone());
        params.addValue("address", recipientDTO.getAddress());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public RecipientDTO findById(Long id) throws DataAccessException {
        String sql = "SELECT * FROM Recipient WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, recipientRowMapper());
    }

    @Override
    public List<RecipientDTO> findAll() throws DataAccessException {
        String sql = "SELECT * FROM Recipient";

        return jdbcTemplate.query(sql, recipientRowMapper());
    }

    @Override
    public void update(RecipientDTO recipientDTO) throws DataAccessException {
        String sql = "UPDATE Recipient SET Name = :name, Email = :email, Phone = :phone, Address = :address WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", recipientDTO.getName());
        params.addValue("email", recipientDTO.getEmail());
        params.addValue("phone", recipientDTO.getPhone());
        params.addValue("address", recipientDTO.getAddress());
        params.addValue("id", recipientDTO.getId());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteById(Long id) throws DataAccessException {
        String sql = "DELETE FROM Recipient WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    private RowMapper<RecipientDTO> recipientRowMapper() {
        return (rs, rowNum) -> {
            RecipientDTO recipientDTO = new RecipientDTO();
            recipientDTO.setId(rs.getLong("ID"));
            recipientDTO.setName(rs.getString("Name"));
            recipientDTO.setEmail(rs.getString("Email"));
            recipientDTO.setPhone(rs.getString("Phone"));
            recipientDTO.setAddress(rs.getString("Address"));
            return recipientDTO;
        };
    }
}
