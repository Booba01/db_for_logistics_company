package org.udsu.warehouse.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.udsu.warehouse.repository.dto.ShipmentDTO;
import org.udsu.warehouse.repository.dto.ProductDTO;

@Repository
public class ShipmentRepository extends BaseRepository implements IShipmentRepository {

    public ShipmentRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public void insert(ShipmentDTO shipmentDTO) throws DataAccessException {
        String sql = "INSERT INTO Shipment (ShipmentDate, Quantity, Cost, StorageID, SenderID, RecipientID) VALUES (:shipmentDate, :quantity, :cost, :storageId, :senderId, :recipientId)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("shipmentDate", shipmentDTO.getShipmentDate());
        params.addValue("quantity", shipmentDTO.getQuantity());
        params.addValue("cost", shipmentDTO.getCost());
        params.addValue("storageId", shipmentDTO.getStorageId());
        params.addValue("senderId", shipmentDTO.getSenderId());
        params.addValue("recipientId", shipmentDTO.getRecipientId());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public ShipmentDTO findById(Long id) throws DataAccessException {
        String sql = "SELECT * FROM Shipment WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, shipmentRowMapper());
    }

    @Override
    public List<ShipmentDTO> findAll() throws DataAccessException {
        String sql = "SELECT * FROM Shipment";

        return jdbcTemplate.query(sql, shipmentRowMapper());
    }

    @Override
    public void update(ShipmentDTO shipmentDTO) throws DataAccessException {
        String sql = "UPDATE Shipment SET ShipmentDate = :shipmentDate, Quantity = :quantity, Cost = :cost, StorageID = :storageId, SenderID = :senderId, RecipientID = :recipientId WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("shipmentDate", shipmentDTO.getShipmentDate());
        params.addValue("quantity", shipmentDTO.getQuantity());
        params.addValue("cost", shipmentDTO.getCost());
        params.addValue("storageId", shipmentDTO.getStorageId());
        params.addValue("senderId", shipmentDTO.getSenderId());
        params.addValue("recipientId", shipmentDTO.getRecipientId());
        params.addValue("id", shipmentDTO.getId());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteById(Long id) throws DataAccessException {
        String sql = "DELETE FROM Shipment WHERE ID = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<ProductDTO> findProductsByShipmentId(Long shipmentId) throws DataAccessException {
        String sql = "SELECT p.* FROM Product p INNER JOIN ProductInShipment ps ON p.ID = ps.ProductID WHERE ps.ShipmentID = :shipmentId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("shipmentId", shipmentId);

        return jdbcTemplate.query(sql, params, productRowMapper());
    }

    @Override
    public void addProductToShipment(Long shipmentId, Long productId) throws DataAccessException {
        String sql = "INSERT INTO ProductInShipment (ShipmentID, ProductID) VALUES (:shipmentId, :productId)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("shipmentId", shipmentId);
        params.addValue("productId", productId);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void removeProductFromShipment(Long shipmentId, Long productId) throws DataAccessException {
        String sql = "DELETE FROM ProductInShipment WHERE ShipmentID = :shipmentId AND ProductID = :productId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("shipmentId", shipmentId);
        params.addValue("productId", productId);

        jdbcTemplate.update(sql, params);
    }

    private RowMapper<ShipmentDTO> shipmentRowMapper() {
        return (rs, rowNum) -> {
            ShipmentDTO shipmentDTO = new ShipmentDTO();
            shipmentDTO.setId(rs.getLong("ID"));
            shipmentDTO.setShipmentDate(rs.getDate("ShipmentDate"));
            shipmentDTO.setQuantity(rs.getInt("Quantity"));
            shipmentDTO.setCost(rs.getBigDecimal("Cost"));
            shipmentDTO.setStorageId(rs.getLong("StorageID"));
            shipmentDTO.setSenderId(rs.getLong("SenderID"));
            shipmentDTO.setRecipientId(rs.getLong("RecipientID"));
            return shipmentDTO;
        };
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

