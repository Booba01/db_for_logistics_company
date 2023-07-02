package org.udsu.warehouse.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.udsu.warehouse.repository.dto.DealerDTO;

@Repository
public class DealerRepository extends BaseRepository implements IDealerRepository {

    public DealerRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void insert(DealerDTO dealerDTO) throws DataAccessException {
        String sql = "INSERT INTO Dealer (Name, Email, Phone, Address) VALUES (:name, :email, :phone, :address)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", dealerDTO.getName());
        params.addValue("email", dealerDTO.getEmail());
        params.addValue("phone", dealerDTO.getPhone());
        params.addValue("address", dealerDTO.getAddress());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public DealerDTO findById(Long id) throws DataAccessException {
        String sql = "SELECT * FROM Dealer WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, dealerRowMapper());
    }

    @Override
    public List<DealerDTO> findAll() throws DataAccessException {
        String sql = "SELECT * FROM Dealer";

        return jdbcTemplate.query(sql, dealerRowMapper());
    }

    @Override
    public void update(DealerDTO dealerDTO) throws DataAccessException {
        String sql = "UPDATE Dealer SET Name = :name, Email = :email, Phone = :phone, Address = :address WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", dealerDTO.getName());
        params.addValue("email", dealerDTO.getEmail());
        params.addValue("phone", dealerDTO.getPhone());
        params.addValue("address", dealerDTO.getAddress());
        params.addValue("id", dealerDTO.getId());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteById(Long id) throws DataAccessException {
        String sql = "DELETE FROM Dealer WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    private RowMapper<DealerDTO> dealerRowMapper() {
        return (rs, rowNum) -> {
            DealerDTO dealerDTO = new DealerDTO();
            dealerDTO.setId(rs.getLong("ID"));
            dealerDTO.setName(rs.getString("Name"));
            dealerDTO.setEmail(rs.getString("Email"));
            dealerDTO.setPhone(rs.getString("Phone"));
            dealerDTO.setAddress(rs.getString("Address"));
            return dealerDTO;
        };
    }
}
