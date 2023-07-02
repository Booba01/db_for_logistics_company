package org.udsu.warehouse.repository.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal weight;
    private BigDecimal volume;
    private Long dealerId;
}
