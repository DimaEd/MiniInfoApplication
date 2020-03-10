package com.ednach.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String productName;
    private int cost;
    private Set<ProducerResponseDto> producers;
}
