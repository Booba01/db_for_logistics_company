package org.udsu.warehouse.repository.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ShipmentDTO {
    private Long id;
    private java.sql.Date shipmentDate;
    private Integer quantity;
    private BigDecimal cost;
    private Long storageId;
    private Long senderId;
    private Long recipientId;
}
