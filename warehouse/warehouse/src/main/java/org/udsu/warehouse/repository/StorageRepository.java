package org.udsu.warehouse.repository;


import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.udsu.warehouse.repository.dto.ProductDTO;
import org.udsu.warehouse.repository.dto.StorageDTO;

@Repository
public class StorageRepository extends BaseRepository implements IStorageRepository {

    public StorageRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void insert(StorageDTO storageDTO) throws DataAccessException {
        String sql = "INSERT INTO Storage (Name, Address) VALUES (:name, :address)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", storageDTO.getName());
        params.addValue("address", storageDTO.getAddress());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public StorageDTO findById(Long id) throws DataAccessException {
        String sql = "SELECT * FROM Storage WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, storageRowMapper());
    }

    @Override
    public List<StorageDTO> findAll() throws DataAccessException {
        String sql = "SELECT * FROM Storage";

        return jdbcTemplate.query(sql, storageRowMapper());
    }

    @Override
    public void update(StorageDTO storageDto) throws DataAccessException {
        String sql = "UPDATE Storage SET Name = :name, Address = :address WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", storageDto.getName());
        params.addValue("address", storageDto.getAddress());
        params.addValue("id", storageDto.getId());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteById(Long id) throws DataAccessException {
        String sql = "DELETE FROM Storage WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<ProductDTO> findProductsByStorageId(Long id) throws DataAccessException {
        String sql = "SELECT p.* FROM Product p " +
                "INNER JOIN ProductInStorage ps ON p.ID = ps.ProductID " +
                "WHERE ps.StorageID = :storageId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("storageId", id);

        return jdbcTemplate.query(sql, params, productRowMapper());
    }

    @Override
    public void addProductToStorage(Long storageId, Long productId, int quantity) throws DataAccessException {
        String sql = "INSERT INTO ProductInStorage (ItemID, WarehouseID, DealerID, Quantity, ProductID, StorageID) " +
                "VALUES (nextval('item_sequence'), nextval('warehouse_sequence'), " +
                "(SELECT DealerID FROM Product WHERE ID = :productId), :quantity, :productId, :storageId)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("productId", productId);
        params.addValue("quantity", quantity);
        params.addValue("storageId", storageId);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeProductFromStorage(Long storageId, Long productId) throws DataAccessException {
        String sql = "DELETE FROM ProductInStorage WHERE StorageID = :storageId AND ProductID = :productId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("storageId", storageId);
        params.addValue("productId", productId);

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

    private RowMapper<StorageDTO> storageRowMapper() {
        return (rs, rowNum) -> {
            StorageDTO storageDto = new StorageDTO();
            storageDto.setId(rs.getLong("ID"));
            storageDto.setName(rs.getString("Name"));
            storageDto.setAddress(rs.getString("Address"));
            return storageDto;
        };
    }
}
