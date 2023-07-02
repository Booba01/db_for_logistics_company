package org.udsu.warehouse.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.udsu.warehouse.repository.dto.ProductDTO;

@Repository
public class ProductRepository extends BaseRepository implements IProductRepository {

    public ProductRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void insert(ProductDTO productDTO) throws DataAccessException {
        String sql = "INSERT INTO Product (Name, Weight, Volume, DealerID) VALUES (:name, :weight, :volume, :dealerId)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", productDTO.getName());
        params.addValue("weight", productDTO.getWeight());
        params.addValue("volume", productDTO.getVolume());
        params.addValue("dealerId", productDTO.getDealerId());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public ProductDTO findById(Long id) throws DataAccessException {
        String sql = "SELECT * FROM Product WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, productRowMapper());
    }

    @Override
    public List<ProductDTO> findAll() throws DataAccessException {
        String sql = "SELECT * FROM Product";

        return jdbcTemplate.query(sql, productRowMapper());
    }

    @Override
    public List<ProductDTO> findByDealerId(Long dealerId) throws DataAccessException {
        String sql = "SELECT * FROM Product WHERE DealerID = :dealerId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dealerId", dealerId);

        return jdbcTemplate.query(sql, params, productRowMapper());
    }

    @Override
    public void update(ProductDTO productDTO) throws DataAccessException {
        String sql = "UPDATE Product SET Name = :name, Weight = :weight, Volume = :volume, DealerID = :dealerId WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", productDTO.getName());
        params.addValue("weight", productDTO.getWeight());
        params.addValue("volume", productDTO.getVolume());
        params.addValue("dealerId", productDTO.getDealerId());
        params.addValue("id", productDTO.getId());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteById(Long id) throws DataAccessException {
        String sql = "DELETE FROM Product WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    private RowMapper<ProductDTO> productRowMapper() {
        return (rs, rowNum) -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(rs.getLong("ID"));
            productDTO.setName(rs.getString("Name"));
            productDTO.setWeight(rs.getBigDecimal("Weight"));
            productDTO.setVolume(rs.getBigDecimal("Volume"));
            productDTO.setDealerId(rs.getLong("DealerID"));
            return productDTO;
        };
    }
}

