package org.udsu.warehouse.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.udsu.warehouse.repository.dto.SenderDTO;

@Repository
public class SenderRepository extends BaseRepository implements ISenderRepository {

    public SenderRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void insert(SenderDTO senderDTO) throws DataAccessException {
        String sql = "INSERT INTO Sender (Name, Email, Phone, Address) VALUES (:name, :email, :phone, :address)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", senderDTO.getName());
        params.addValue("email", senderDTO.getEmail());
        params.addValue("phone", senderDTO.getPhone());
        params.addValue("address", senderDTO.getAddress());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public SenderDTO findById(Long id) throws DataAccessException {
        String sql = "SELECT * FROM Sender WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, senderRowMapper());
    }

    @Override
    public List<SenderDTO> findAll() throws DataAccessException {
        String sql = "SELECT * FROM Sender";

        return jdbcTemplate.query(sql, senderRowMapper());
    }

    @Override
    public void update(SenderDTO senderDTO) throws DataAccessException {
        String sql = "UPDATE Sender SET Name = :name, Email = :email, Phone = :phone, Address = :address WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", senderDTO.getName());
        params.addValue("email", senderDTO.getEmail());
        params.addValue("phone", senderDTO.getPhone());
        params.addValue("address", senderDTO.getAddress());
        params.addValue("id", senderDTO.getId());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteById(Long id) throws DataAccessException {
        String sql = "DELETE FROM Sender WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    private RowMapper<SenderDTO> senderRowMapper() {
        return (rs, rowNum) -> {
            SenderDTO senderDTO = new SenderDTO();
            senderDTO.setId(rs.getLong("ID"));
            senderDTO.setName(rs.getString("Name"));
            senderDTO.setEmail(rs.getString("Email"));
            senderDTO.setPhone(rs.getString("Phone"));
            senderDTO.setAddress(rs.getString("Address"));
            return senderDTO;
        };
    }
}
