package org.udsu.warehouse.repository.dto;

import lombok.Data;

@Data
public class RecipientDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}
