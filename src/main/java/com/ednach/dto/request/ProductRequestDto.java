package com.ednach.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter

public class ProductRequestDto {
    private Long id;
    private String productName;
    private int cost;
    private Set<Long> producerIds;
    private Long userId;

    public ProductRequestDto(Set<Long> producerIds) {
        this.producerIds = producerIds;
    }

    public ProductRequestDto() {
    }
}
